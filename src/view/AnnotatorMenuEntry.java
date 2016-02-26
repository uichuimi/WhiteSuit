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

package view;

import javafx.scene.image.Image;

/**
 * Created by uichuimi on 16/02/16.
 *
 * @author Lorente-Arencibia, Pascual (pasculorente@gmail.com)
 */
public class AnnotatorMenuEntry implements WToolMenuEntry {
    @Override
    public String getName() {
        return "VEP annotator";
    }

    @Override
    public String getDescription() {
        return "Annotate variants using VEP from Ensembl (R)";
    }

    @Override
    public Image getIcon() {
        return new Image("img/annotate.png");
    }

    @Override
    public ToolInterface getTool() {
        return AnnotatorInterface.getInstance();
    }
}