package bloodandhell.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AvaricePower extends AbstractGameAction {
    private AbstractPlayer player;
    private AbstractMonster target;
    private int energyOnUse;

    public AvaricePower(AbstractPlayer player, AbstractMonster target, int energyOnUse) {
        this.player = player;
        this.target = target;
        this.energyOnUse = energyOnUse;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
    }

    @Override
    public void update() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.costForTurn == -1) {
                // Loop for every energy used
                for (int i = 0; i < energyOnUse; i++) {
                    if (target != null && !target.isDeadOrEscaped()) {
                        addToBot(new DamageAction(target, new com.megacrit.cardcrawl.cards.DamageInfo(player, 1, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.THORNS), AttackEffect.POISON));
                        addToBot(new HealAction(player, player, 1));
                        addToBot(new GainEnergyAction(1));
                    }
                }
            }
        }
        this.isDone = true;
    }
}
