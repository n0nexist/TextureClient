package client.texture.textureclient.ui;

import client.texture.textureclient.modules.TextureModule;
import client.texture.textureclient.utils.TextureUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class ClickGui extends Screen {

    int maxmodules = 75;
    int offset = 0;

    boolean canscrolldown = true;

    private TextFieldWidget textBox;

    String searchString = "";

    public ClickGui() {
        super(Text.of("Click§f§b§uGUI"));
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollAmount) {
        this.offset += (scrollAmount>0)? ((offset>0)?-1:0) : (canscrolldown)? 1:0;
        this.clearAndInit();
        return true;
    }

    protected void init() {
        initWidgets();
        Objects.requireNonNull(this.textRenderer);
        addDrawableChild(new TextWidget(0, 20, this.width, 9, this.title, this.textRenderer));

        textBox = new TextFieldWidget(this.textRenderer, 10, this.height - 30, this.width - 20, 20, Text.of(searchString));
        textBox.setMaxLength(100);
        addDrawableChild(textBox);
    }

    private Text getButtonString(String buttonText){
        return Text.of((TextureUtils.isModuleToggled(buttonText)?"§b":"§f")+buttonText);
    }

    private ButtonWidget createCustomButton(int x, int y, String buttonText) {
        ButtonWidget button = ButtonWidget.builder(getButtonString(buttonText), btn -> {
                    TextureUtils.toggle(buttonText);
                    btn.setMessage(getButtonString(buttonText));
                })
                .width(102)
                .build();
        SimplePositioningWidget.setPos(button, 0, 0, x, y, 0.5F, 0.5F);
        return button;
    }

    private void generateButton(GridWidget.Adder adder, GridWidget gridWidget, String text){
        ButtonWidget customButton = createCustomButton(150, 80, text);
        adder.add(customButton, 1, gridWidget.copyPositioner().marginTop(5).marginRight(10).marginLeft(10));
    }

    private void initWidgets() {
        GridWidget gridWidget = new GridWidget();
        gridWidget.getMainPositioner().margin(4, 4, 4, 0);
        GridWidget.Adder adder = gridWidget.createAdder(5);

        ArrayList<TextureModule> modules = TextureUtils.getModulesInAlphabeticalOrder();

        int generatedbuttons = 0;

        int a = 0;
        for (TextureModule module : modules) {
            a += 1;
            if (a <= maxmodules+(offset*5) && a>(offset)*5 && (searchString == "" || module.name.toLowerCase().contains(searchString.toLowerCase()))) {
                generateButton(adder, gridWidget, module.name);
                generatedbuttons++;
            }
        }

        canscrolldown = generatedbuttons==maxmodules;

        gridWidget.refreshPositions();
        SimplePositioningWidget.setPos(gridWidget, 0, 0, this.width, this.height, 0.5F, 0.5F);
        gridWidget.forEachChild(this::addDrawableChild);
    }

    public void tick() {
        super.tick();
        textBox.tick();
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        textBox.render(context, mouseX, mouseY, delta);
        if (searchString != textBox.getText()){
            onTextBoxTextChanged(textBox.getText());
        }
    }

    private void onTextBoxTextChanged(String newText) {
        searchString = newText;
        System.out.println(newText);
        offset = 0;
        this.clearChildren();
        this.initWidgets();
    }

}
