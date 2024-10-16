package bloodandhell.patches;

import bloodandhell.panels.BloodPanel;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch2(clz = AbstractPlayer.class, method = "<class>")
public class BloodFury {
    public static SpireField<BloodPanel> BloodPanel = new SpireField<>(BloodPanel::new);
    public static SpireField<BloodPanel> BloodPanel2 = new SpireField<>(BloodPanel::new);

}
