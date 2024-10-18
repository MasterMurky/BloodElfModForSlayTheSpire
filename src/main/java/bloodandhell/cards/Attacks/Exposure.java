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
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

public class Exposure extends BaseCard {
    public static final String ID = makeID(Exposure.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 4;
    private static final int DEBUFF_APPLICATIONS = 2;

    public Exposure() {
        super(ID, info);
        setDamage(DAMAGE,UPG_DAMAGE);    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, 2, false),1 ));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Exposure();
    }
}
