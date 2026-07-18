package bloodandhell.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SacramentGoldPower extends AbstractPower {
    public static final String POWER_ID = "bloodandhell:SacramentGoldPower";
    private final int goldPerKill;

    public SacramentGoldPower(AbstractCreature owner, int goldPerKill) {
        this.name = "Sacrament";
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
        this.description = "Gain " + goldPerKill + " gold whenever an enemy dies this combat.";
    }
}
