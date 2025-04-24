package bloodandhell.cards.Attacks;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ExecutionStrike extends BaseCard {
    public static final String ID = makeID(ExecutionStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;

    private static final int EXEC_THRESHOLD = 20;
    private static final int UPG_EXEC_THRESHOLD = 10;

    private static final int BOOSTED_DAMAGES = 18;
    private static final int UPG_BOOSTED_DAMAGES = 27;

    private int boostedDamage;

    public ExecutionStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(EXEC_THRESHOLD, UPG_EXEC_THRESHOLD);
        this.boostedDamage = BOOSTED_DAMAGES;
        updateDescription(false);
        tags.add(CardTags.STRIKE); //This tag marks it as a Strike card for the purposes of Perfected Strike and any similar modded effects
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int finalDamage = this.damage;
        if (m.currentHealth <= this.magicNumber) {
            finalDamage = this.boostedDamage;
        }

        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeMagicNumber(UPG_EXEC_THRESHOLD);
            this.boostedDamage = UPG_BOOSTED_DAMAGES;
            updateDescription(true);
        }
    }

    private void updateDescription(boolean upgraded) {
        String baseDesc = cardStrings.DESCRIPTION;
        if (upgraded) {
            baseDesc = cardStrings.UPGRADE_DESCRIPTION;
        }

        this.rawDescription = baseDesc.replace("!BD!", String.valueOf(this.boostedDamage));
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new ExecutionStrike();
    }
}
