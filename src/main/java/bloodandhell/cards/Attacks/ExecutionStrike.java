package bloodandhell.cards.Attacks;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;

public class ExecutionStrike extends BaseCard {
    public static final String ID = makeID(ExecutionStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 1;

    private static final int EXEC_THRESHOLD = 20;
    private static final int UPG_EXEC_THRESHOLD = 5; // 20 -> 25

    public ExecutionStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(EXEC_THRESHOLD, UPG_EXEC_THRESHOLD);
        tags.add(CardTags.STRIKE); //This tag marks it as a Strike card for the purposes of Perfected Strike and any similar modded effects
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean isExecution = m != null && m.currentHealth <= this.magicNumber;
        // Exécution nette : dégâts égaux aux PV actuels de la cible (ni gâchis, ni PV restants),
        // dans les deux versions de la carte.
        int finalDamage = isExecution ? m.currentHealth : this.damage;
        if (isExecution) {
            // Vend visuellement l'exécution quand le seuil est atteint.
            addToBot(new VFXAction(new DieDieDieEffect(), 0.2F));
        }

        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeMagicNumber(UPG_EXEC_THRESHOLD);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ExecutionStrike();
    }
}
