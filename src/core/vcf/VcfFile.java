/*
 Copyright (c) UICHUIMI 02/2016

 This file is part of WhiteSuit.

 WhiteSuit is free software: you can redistribute it and/or modify it under the terms of the
 GNU General Public License as published by the Free Software Foundation, either version 3 of
 the License, or (at your option) any later version.

 WhiteSuit is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with Foobar.
 If not, see <http://www.gnu.org/licenses/>.
 */

package core.vcf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

/**
 * Stores in memory a Vcf file data. Variant Call Format (VCF) Version 4.2.
 *
 * @author Lorente Arencibia, Pascual <pasculorente@gmail.com>
 */
public class VcfFile {

    private final ObservableList<Variant> variants = FXCollections.observableArrayList();
    private final VcfHeader header;

    private File file;


    public VcfFile(File file) {
        this.file = file;
        this.header = new VcfHeader();
        loadFileContent();
    }

    private void loadFileContent() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            readLines(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readLines(final BufferedReader reader) {
        reader.lines().forEach(line -> {
            if (!line.startsWith("#")) variants.add(VariantFactory.createVariant(line, this));
            else header.addHeader(line);
        });
    }

    /**
     * Get the list of variants.
     *
     * @return the list of variants
     */
    public ObservableList<Variant> getVariants() {
        return variants;
    }

    public File getFile() {
        return file;
    }

    public VcfHeader getHeader() {
        return header;
    }

    /**
     * Save current data to a file.
     *
     * @param file target file
     */
    public void save(File file) {
        save(file, variants);
    }

    /**
     * Save list of variants passed by args, using this VCFFile for headers into the file.
     *
     * @param file     target file
     * @param variants list of variants
     */
    public void save(File file, ObservableList<Variant> variants) {
        if (file.exists() && !file.delete()) System.err.println("No access on " + file);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(getHeader().toString());
            for (Variant variant : variants) {
                writer.write(variant.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
