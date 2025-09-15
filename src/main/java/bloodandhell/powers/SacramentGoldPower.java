package bloodandhell.powers;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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
        updateDescription();
    }

    public void onMonsterDeath(AbstractMonster m) {
        if (m.isDeadOrEscaped()) {
            AbstractDungeon.actionManager.addToBottom(new GainGoldAction(goldPerKill));
        }
    }

    @Override
    public void updateDescription() {
        this.description = "Gain " + goldPerKill + " gold whenever an enemy dies this combat.";
    }
}
