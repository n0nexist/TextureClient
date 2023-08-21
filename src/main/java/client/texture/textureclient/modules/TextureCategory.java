package client.texture.textureclient.modules;

public enum TextureCategory {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    MISC("Misc");

    private final String displayName;

    TextureCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
