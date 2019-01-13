package com.nalepka.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nalepka.model.Option;
import com.nalepka.model.Unit;
import com.nalepka.model.Weapon;
import com.nalepka.model.dataHolder.OptionsDataHolder;
import com.nalepka.model.dataHolder.SelectorDataHolder;
import com.nalepka.model.dataHolder.UnitDataHolder;
import com.nalepka.repository.*;
import com.nalepka.service.CreateListService;
import com.nalepka.utils.PaintTableColorPicker;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@Service
public class CreateListServiceImpl implements CreateListService {
    private SelectorDao selectorDao;
    private UnitDao unitDao;
    private OptionDao optionDao;
    private WeaponDao weaponDao;
    private RuleDao ruleDao;

    public CreateListServiceImpl(SelectorDao selectorDao, UnitDao unitDao, OptionDao optionDao, WeaponDao weaponDao, RuleDao ruleDao) {
        this.selectorDao = selectorDao;
        this.unitDao = unitDao;
        this.optionDao = optionDao;
        this.weaponDao = weaponDao;
        this.ruleDao = ruleDao;
    }

    public Document createPdfFromJson(String json) throws IOException, DocumentException {
        final ObjectMapper mapper = new ObjectMapper();

        final SelectorDataHolder selector = mapper.readValue(json, mapper.getTypeFactory().constructType(SelectorDataHolder.class));

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("armyLists/ArmyList.pdf"));
        document.open();

        Paragraph title = new Paragraph();
        title.add(selectorDao.findById(selector.getId()).get().getName() + " - " + selector.getPoints());
        document.add(title);

        selector.getUnits().forEach(u -> {
            try {
                document.add(createUnitTable(u));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });

        document.close();

        return null;
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
        PdfPCell weaponsHeader = new PdfPCell(createWeaponTable((ArrayList<String>) weaponHeaderArguments,BaseColor.GRAY));
        java.util.List<PdfPCell> weapons = createWeaponsList(unitDataHolder);

        unitName.setColspan(2);
        weaponsHeader.setColspan(4);
        weapons.forEach(w -> w.setColspan(4));

        table.addCell(unitName);
        table.addCell(experienceLevel);
        table.addCell(points);
        table.addCell(weaponsHeader);
        weapons.forEach(table::addCell);

        return table;
    }

    private java.util.List<PdfPCell> createWeaponsList(UnitDataHolder unitDataHolder){
        final Unit unit = unitDao.findById(unitDataHolder.getId()).get();
        java.util.List<PdfPCell> result = new ArrayList<>();
        PaintTableColorPicker colorPicker = new PaintTableColorPicker();

        unit.getWeapons().forEach(w -> {
            java.util.List <String> weaponHeaderArguments = new ArrayList<>();

            weaponHeaderArguments.add("n/a");
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

                weaponHeaderArguments.add("n/a");
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

    private PdfPCell createOptionsList(Set<OptionsDataHolder> optionsDataHolder){
        List optionsList = new List(List.UNORDERED);

        optionsDataHolder.forEach(option -> optionsList.add(option.getCount() + "x "
                + optionDao.findById(option.getId()).get().getWeaponOrRule()));

        PdfPCell result = new PdfPCell();
        result.addElement(optionsList);
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
