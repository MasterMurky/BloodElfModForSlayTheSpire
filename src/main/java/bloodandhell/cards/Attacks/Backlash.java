package bloodandhell.cards.Attacks;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class Backlash extends BaseCard {
    public static final String ID = makeID(Backlash.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 12;
    private static final int UPGRADE_DAMAGE = 5;
    private static final int WEAK_VULNERABLE_TURNS = 1;

    public Backlash() {
        super(ID, info);
        setDamage(DAMAGE, UPGRADE_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        addToBot(new ApplyPowerAction(p, p, new WeakPower(p, WEAK_VULNERABLE_TURNS, false), WEAK_VULNERABLE_TURNS));
        addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, WEAK_VULNERABLE_TURNS, false), WEAK_VULNERABLE_TURNS));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);  // Augmentation des dégâts à 17 lors de l'amélioration
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Backlash();
    }
}
