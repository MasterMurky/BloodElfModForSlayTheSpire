package bloodandhell.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class DoubleImpactPower extends AbstractPower {
    // Distinct de la carte "DoubleImpact" : un ID de pouvoir prefixé par modID est requis pour que
    // getPowerStrings() trouve son entrée dans PowerStrings.json (l'ancien ID nu "DoubleImpact"
    // ne correspondait à rien -> nom/description cassés en jeu).
    public static final String POWER_ID = bloodandhell.BasicMod.makeID("DoubleImpactPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean affectsSkills; // To check if it also affects spells

    public DoubleImpactPower(AbstractCreature owner, int amount, boolean affectsSkills) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.affectsSkills = affectsSkills; // same
        updateDescription();
        loadRegion("doubleTap");
    }

    @Override
    public void updateDescription() {
        // Switch descr
        if (this.affectsSkills) {
            this.description = DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0];
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        // Check if it's an attack or a spell (if upgraded)
        if (!card.purgeOnUse && (card.type == AbstractCard.CardType.ATTACK || (this.affectsSkills && card.type == AbstractCard.CardType.SKILL))) {
            flash();

            final AbstractMonster m = action.target != null ? (AbstractMonster) action.target : null;
            final int energyOnUse = card.energyOnUse;

            // La copie est créée en file d'attente (et non immédiatement) pour que les cartes qui
            // modifient leurs propres stats via une action mise en file par leur use() (ex: HeroicStrike,
            // qui augmente baseDamage après avoir infligé ses dégâts) aient déjà appliqué ce changement
            // au moment où la copie hérite de leurs stats : sinon la copie se fige sur les valeurs
            // d'avant boost et inflige les mêmes dégâts (ou moins) que l'original.
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractCard tmp = card.makeSameInstanceOf();
                    AbstractDungeon.player.limbo.addToBottom(tmp);
                    tmp.current_x = card.current_x;
                    tmp.current_y = card.current_y;
                    tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                    tmp.target_y = Settings.HEIGHT / 2.0F;

                    if (m != null) {
                        tmp.calculateCardDamage(m);
                    }

                    tmp.purgeOnUse = true;  // Delete temp card after use
                    AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, energyOnUse, true, true), true);
                    this.isDone = true;
                }
            });
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            // Delete the power at the end of the turn
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }
}
