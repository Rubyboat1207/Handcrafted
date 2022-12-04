package earth.terrarium.handcrafted.block.property;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum DirectionalBlockSide implements StringRepresentable {
    SINGLE("single"),
    LEFT("left"),
    MIDDLE("middle"),
    RIGHT("right");

    private final String name;

    DirectionalBlockSide(String string2) {
        this.name = string2;
    }

    public String toString() {
        return this.name;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}