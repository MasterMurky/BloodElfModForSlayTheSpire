package bloodandhell.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static bloodandhell.BasicMod.makeID;



// can be = null
public class LiquidPain extends BasePotion {
    public static final String ID = makeID("LiquidPain");
    private static final Color LIQUID_COLOR = CardHelper.getColor(255, 0, 0);
    private static final Color HYBRID_COLOR = null; // 2e couleur
    private static final Color SPOTS_COLOR = null; // les points


    // 2nd parameter : the number in the description of the potion (will be doubled with some relics)
    // 4th parameter : the shape of the bottle
    // PotionColor.FIRE (for the colors)

    //isThrown = true; targetRequired = true;
    public LiquidPain() {
        super(ID, 4, PotionRarity.COMMON, PotionSize.BOTTLE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        // playerClass = AbstractPlayer.PlayerClass.IRONCLAD; // If you want the potion to be attached to a specific character, else every character will access it

    }

    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 10));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, potency), potency));
        }
    }
    @Override
    public void addAdditionalTips() {
        //Adding a tooltip for Strength
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.STRENGTH.NAMES[0]), GameDictionary.keywords.get(GameDictionary.STRENGTH.NAMES[0])));
    }
}