package com.nalepka.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nalepka.model.*;
import com.nalepka.model.dataHolder.OptionsDataHolder;
import com.nalepka.model.dataHolder.SelectorDataHolder;
import com.nalepka.model.dataHolder.UnitDataHolder;
import com.nalepka.repository.*;
import com.nalepka.service.GeneratePdfService;
import com.nalepka.utils.PaintTableColorPicker;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class GeneratePdfServiceImpl implements GeneratePdfService {
    private SelectorDao selectorDao;
    private UnitDao unitDao;
    private OptionDao optionDao;
    private WeaponDao weaponDao;
    private RuleDao ruleDao;

    public GeneratePdfServiceImpl(SelectorDao selectorDao, UnitDao unitDao, OptionDao optionDao, WeaponDao weaponDao, RuleDao ruleDao) {
        this.selectorDao = selectorDao;
        this.unitDao = unitDao;
        this.optionDao = optionDao;
        this.weaponDao = weaponDao;
        this.ruleDao = ruleDao;
    }

    public ByteArrayOutputStream createPdfFromJson(String json) throws IOException, DocumentException {
        final ObjectMapper mapper = new ObjectMapper();

        final SelectorDataHolder selector = mapper.readValue(json, mapper.getTypeFactory().constructType(SelectorDataHolder.class));

        final Font titleFont = new Font(Font.FontFamily.HELVETICA  , 20, Font.BOLD);
        final Font rulesFont = new Font(Font.FontFamily.HELVETICA  , 16, Font.BOLD);

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        Paragraph title = new Paragraph(selectorDao.
                findById(selector.getId()).get().getName()
                + " - " + selector.getPoints()
                , titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph numberOfUnits = new Paragraph(selector.getUnitsSize() + " units");

        PdfPTable numberOfUnitsTable = new PdfPTable(1);
        PdfPCell numberOfUnitsCell = new PdfPCell(numberOfUnits);
        numberOfUnitsCell.setPaddingTop(20);
        numberOfUnitsCell.setBorder(Rectangle.NO_BORDER);
        numberOfUnitsTable.addCell(numberOfUnitsCell);
        numberOfUnitsTable.setWidthPercentage(100);
        document.add(numberOfUnitsTable);

        selector.getUnits().forEach(u -> {
            try {
                document.add(createUnitTable(u));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });

        Paragraph rulesHeader = new Paragraph("Rules", rulesFont);
        rulesHeader.setAlignment(Element.ALIGN_CENTER);
        rulesHeader.setSpacingBefore(15);
        rulesHeader.setSpacingAfter(15);
        document.add(rulesHeader);

        createRulesSet(selector).forEach(r -> {
            try {
                document.add(r);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });

        document.close();

        return out;
    }

    private PdfPTable createUnitTable(UnitDataHolder unitDataHolder){
        final Unit unit = unitDao.findById(unitDataHolder.getId()).get();
        java.util.List<String> weaponHeaderArguments = new ArrayList<>();
        weaponHeaderArguments.add("Qty");
        weaponHeaderArguments.add("Weapon");
        weaponHeaderArguments.add("Range");
        weaponHeaderArguments.add("Shots");
        weaponHeaderArguments.add("Pen");
        weaponHeaderArguments.add("Special Rules");

        PdfPTable table = new PdfPTable(4);

        PdfPCell unitName = new PdfPCell(new Paragraph(unit.getName()));
        PdfPCell experienceLevel = new PdfPCell(new Paragraph(unitDataHolder.getExperienceLevel().toString()));
        PdfPCell points = new PdfPCell(new Paragraph(calculatePoints(unitDataHolder)));
        PdfPCell additionalModels = new PdfPCell(createNumberOfModelsTable(unitDataHolder));
        PdfPCell weaponsHeader = new PdfPCell(createWeaponTable((ArrayList<String>) weaponHeaderArguments,BaseColor.GRAY));
        java.util.List<PdfPCell> weapons = createWeaponsList(unitDataHolder);

        unitName.setColspan(2);
        additionalModels.setColspan(4);
        weaponsHeader.setColspan(4);
        weapons.forEach(w -> w.setColspan(4));

        table.addCell(unitName);
        table.addCell(experienceLevel);
        table.addCell(points);
        table.addCell(additionalModels);
        table.addCell(weaponsHeader);
        weapons.forEach(table::addCell);

        table.setWidthPercentage(100);

        return table;
    }

    private Set<Paragraph> createRulesSet(SelectorDataHolder selectorDataHolder){
        final java.util.List<Unit> units = new ArrayList<>();
        final java.util.Set<Option> options = new HashSet<>();
        final Set<Rule> rules = new HashSet<>();
        final Set<Paragraph> result = new HashSet<>();
        selectorDataHolder.getUnits().forEach(u -> units.add(unitDao.findById(u.getId()).get()));

        units.forEach(u -> {
            rules.addAll(u.getRules());

            u.getWeapons().forEach(w -> rules.addAll(w.getRules()));
        });

        selectorDataHolder.getUnits().forEach(u -> u.getOptions()
                        .forEach(o -> options.add(optionDao.findById(o.getId()).get())));

        options.forEach(o -> {
            if(o.getRule() != null){
                rules.add(o.getRule());
            }else{
                rules.addAll(o.getWeapon().getRules());
            }
        });

        rules.forEach(r -> result.add(createRuleParagraph(r)));

        return result;
    }

    private Paragraph createRuleParagraph(Rule rule){
        Paragraph ruleParagraph = new Paragraph();
        Chunk ruleName = new Chunk(rule.getName(), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        Chunk ruleDetails = new Chunk(" (" + rule.getSource() + " p: " + rule.getPage() + ") ",  new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.GRAY));
        Chunk ruleDescription = new Chunk("- " + rule.getDescription());
        ruleParagraph.add(ruleName);
        ruleParagraph.add(ruleDetails);
        ruleParagraph.add(ruleDescription);

        ruleParagraph.setSpacingBefore(15);

        return ruleParagraph;
    }

    private java.util.List<PdfPCell> createWeaponsList(UnitDataHolder unitDataHolder){
        final Unit unit = unitDao.findById(unitDataHolder.getId()).get();
        java.util.List<PdfPCell> result = new ArrayList<>();
        PaintTableColorPicker colorPicker = new PaintTableColorPicker();

        unit.getWeapons().forEach(w -> {
            java.util.List <String> weaponHeaderArguments = new ArrayList<>();

            weaponHeaderArguments.add(unitDataHolder.getNumberOfAdditionalModels() + unit.getBaseNumber() + "");
            weaponHeaderArguments.add(w.getName());
            weaponHeaderArguments.add(w.getRange());
            weaponHeaderArguments.add(w.getShots());
            weaponHeaderArguments.add(w.getPenetration());
            weaponHeaderArguments.add(w.getRulesNames());

            result.add(new PdfPCell(createWeaponTable((ArrayList<String>) weaponHeaderArguments, colorPicker.getNextColor())));
            }
        );

        unitDataHolder.getOptions().forEach(option -> {
            if(optionDao.findById(option.getId()).get().getWeapon() != null) {
                final Weapon weapon = optionDao.findById(option.getId()).get().getWeapon();

                java.util.List <String> weaponHeaderArguments = new ArrayList<>();

                weaponHeaderArguments.add(option.getCount().toString());
                weaponHeaderArguments.add(weapon.getName());
                weaponHeaderArguments.add(weapon.getRange());
                weaponHeaderArguments.add(weapon.getShots());
                weaponHeaderArguments.add(weapon.getPenetration());
                weaponHeaderArguments.add(weapon.getRulesNames());

                result.add(new PdfPCell(createWeaponTable((ArrayList<String>) weaponHeaderArguments, colorPicker.getNextColor())));
            }
        });

        return result;
    }

    private String calculatePoints(UnitDataHolder unitDataHolder){
        final Unit unit = unitDao.findById(unitDataHolder.getId()).get();
        Integer points = unit.getCost(unitDataHolder.getExperienceLevel());

        points += unit.getAdditionalCost(unitDataHolder.getExperienceLevel()) * unitDataHolder.getNumberOfAdditionalModels();

        for(OptionsDataHolder o : unitDataHolder.getOptions()){
            final Option option = optionDao.findById(o.getId()).get();
            points += option.getCost() * o.getCount();
        }

        return points.toString();
    }

    private PdfPTable createNumberOfModelsTable(UnitDataHolder unitDataHolder) {
        PdfPTable additionalModelsTable = new PdfPTable(1);
        Unit unit = unitDao.findById(unitDataHolder.getId()).get();

        Integer totalNumberOfModels = unit.getBaseNumber() + unitDataHolder.getNumberOfAdditionalModels();
        String message = (totalNumberOfModels == 1)?"men":"man";

        PdfPCell additionalModels = new PdfPCell(
                new Paragraph(totalNumberOfModels + " " + message + " armed with " + unit.getWeapons().get(0).getName())
        );

        additionalModels.setBorder(Rectangle.NO_BORDER);

        additionalModelsTable.addCell(additionalModels);

        return additionalModelsTable;
    }

    private PdfPTable createWeaponTable(ArrayList<String> values, BaseColor color){
        PdfPTable weaponsHeaderTable = new PdfPTable(14);

        PdfPCell quantity = new PdfPCell(new Paragraph(values.get(0)));
        PdfPCell weapon = new PdfPCell(new Paragraph(values.get(1)));
        PdfPCell range = new PdfPCell(new Paragraph(values.get(2)));
        PdfPCell shots = new PdfPCell(new Paragraph(values.get(3)));
        PdfPCell pen = new PdfPCell(new Paragraph(values.get(4)));
        PdfPCell specialRules = new PdfPCell(new Paragraph(values.get(5)));

        quantity.setColspan(1);
        weapon.setColspan(4);
        range.setColspan(2);
        shots.setColspan(2);
        pen.setColspan(2);
        specialRules.setColspan(3);

        quantity.setBorder(Rectangle.NO_BORDER);
        weapon.setBorder(Rectangle.NO_BORDER);
        range.setBorder(Rectangle.NO_BORDER);
        shots.setBorder(Rectangle.NO_BORDER);
        pen.setBorder(Rectangle.NO_BORDER);
        specialRules.setBorder(Rectangle.NO_BORDER);

        quantity.setBackgroundColor(color);
        weapon.setBackgroundColor(color);
        range.setBackgroundColor(color);
        shots.setBackgroundColor(color);
        pen.setBackgroundColor(color);
        specialRules.setBackgroundColor(color);

        weaponsHeaderTable.addCell(quantity);
        weaponsHeaderTable.addCell(weapon);
        weaponsHeaderTable.addCell(range);
        weaponsHeaderTable.addCell(shots);
        weaponsHeaderTable.addCell(pen);
        weaponsHeaderTable.addCell(specialRules);

        return weaponsHeaderTable;
    }
}
