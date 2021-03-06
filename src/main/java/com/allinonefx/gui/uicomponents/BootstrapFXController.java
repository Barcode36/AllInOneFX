/*
 * Copyright (c) 2015-2017 Andres Almiray
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.allinonefx.gui.uicomponents;

import com.allinonefx.controllers.MainController;
import io.datafx.controller.ViewController;
import java.net.URL;
import static java.nio.file.Files.lines;
import java.nio.file.Paths;
import static java.util.stream.Collectors.joining;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javax.annotation.PostConstruct;

@ViewController(value = "/fxml/ui/AnchorFX.fxml", title = "BootstrapFX")
public class BootstrapFXController {
    
    @FXML
    private StackPane root;
    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws Exception {
        MainController.lblTitle.setText("BootstrapFX");
        TabPane tabPane = new TabPane();

        tabPane.getTabs().add(new DemoTab("Buttons", "buttons.fxml"));
        tabPane.getTabs().add(new DemoTab("Labels", "labels.fxml"));
        tabPane.getTabs().add(new DemoTab("Alerts", "alerts.fxml"));
        tabPane.getTabs().add(new DemoTab("Panels", "panels.fxml"));
        tabPane.getTabs().add(new DemoTab("Headings", "text.fxml"));
        tabPane.getTabs().add(new DemoTab("Text ", "text2.fxml"));
        tabPane.getTabs().add(new DemoTab("Paragraph ", "paragraph.fxml"));
        tabPane.getTabs().add(new DemoTab("Button Groups ", "button_groups.fxml"));
        tabPane.getTabs().add(new DemoTab("SplitMenuButtons", "split_menu_buttons.fxml"));

//        Scene scene = root.getScene();
//        scene.getStylesheets().addAll(
//            "bootstrapfx.css",
//            "fxml/ui/bootstrapfx/sampler.css",
//            "fxml/ui/bootstrapfx/xml-highlighting.css");

        
        root.getChildren().add(tabPane);

    }

    private static class DemoTab extends Tab {
        private DemoTab(String title, String sourceFile) throws Exception {
            super(title);
            setClosable(false);

            TabPane content = new TabPane();
            setContent(content);
            content.setSide(Side.BOTTOM);

            Tab widgets = new Tab("Widgets");
            widgets.setClosable(false);
            URL location = getClass().getResource("/fxml/ui/bootstrapfx/" + sourceFile);
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            Node node = fxmlLoader.load();
            widgets.setContent(node);

            Tab source = new Tab("Source");
            source.setClosable(false);
            XMLEditor editor = new XMLEditor();
            editor.setEditable(false);

            String text = lines(Paths.get(getClass().getResource("/fxml/ui/bootstrapfx/" + sourceFile).toURI())).collect(joining("\n"));
            editor.setText(text);
            source.setContent(editor);

            content.getTabs().addAll(widgets, source);
        }
    }
}
