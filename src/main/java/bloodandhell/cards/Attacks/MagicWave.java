package bloodandhell.cards.Attacks;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import bloodandhell.util.SelfDamageTracker;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class MagicWave extends BaseCard {
    public static final String ID = makeID(MagicWave.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            3
    );

    public MagicWave() {
        super(ID, info);
        this.isMultiDamage = true; // MultiDMG
        this.baseDamage = SelfDamageTracker.totalDamageThisCombat; // Initialize
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Variable
        int damageTaken = SelfDamageTracker.totalDamageThisCombat;
        this.baseDamage = damageTaken;

        // Visuals
        addToBot((AbstractGameAction)new SFXAction("ATTACK_PIERCING_WAIL"));
        if (Settings.FAST_MODE) {
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.PURPLE_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
        } else {
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.PURPLE_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));
        }

        // Damage all the ennemies
        addToBot(new DamageAllEnemiesAction(
                p,
                DamageInfo.createDamageMatrix(damageTaken, true),
                DamageInfo.DamageType.NORMAL,
                AbstractGameAction.AttackEffect.NONE
        ));

    }

    @Override
    public void applyPowers() {
        this.baseDamage = SelfDamageTracker.totalDamageThisCombat;
        super.applyPowers();

        // Dynamic description
        this.rawDescription = "Deal (" + this.baseDamage + ") damage to ALL enemies. NL Damage equal to Bloodhunt.";
        initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicWave();
    }
}
