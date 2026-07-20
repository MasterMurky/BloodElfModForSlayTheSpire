package bloodandhell.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SacramentGoldPower extends AbstractPower {
    public static final String POWER_ID = bloodandhell.BasicMod.makeID("SacramentGoldPower");
    private final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private final int goldPerKill;

    public SacramentGoldPower(AbstractCreature owner, int goldPerKill) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.goldPerKill = goldPerKill;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("devotion");
        updateDescription();
    }

    public int getGoldPerKill() {
        return goldPerKill;
    }

    @Override
    public void updateDescription() {
        this.description = String.format(powerStrings.DESCRIPTIONS[0], goldPerKill);
    }
}
