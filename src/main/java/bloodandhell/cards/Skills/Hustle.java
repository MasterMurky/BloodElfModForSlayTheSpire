package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.cards.DamageInfo;

public class Hustle extends BaseCard {
    public static final String ID = makeID(Hustle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL,
            0
    );

    private static final int DAMAGE = 1;
    private static final int STRENGTH = 1;
    private static final int UPGRADE_PLAYER_STRENGTH = 2;

    public Hustle() {
        super(ID, info);
        setMagic(STRENGTH, UPGRADE_PLAYER_STRENGTH);
        this.rawDescription = "Inflige 1 dégât à vous et tous les ennemis. Donne 1 de Force temporaire à vous et à tous les ennemis."; // Description par défaut
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(p, new DamageInfo(p, DAMAGE, DamageInfo.DamageType.THORNS)));

        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDeadOrEscaped()) {
                addToBot(new DamageAction(monster, new DamageInfo(p, DAMAGE, DamageInfo.DamageType.THORNS)));
            }
        }

        int playerStrength = this.upgraded ? UPGRADE_PLAYER_STRENGTH : STRENGTH;
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, playerStrength), playerStrength));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, playerStrength), playerStrength));

        int enemyStrength = STRENGTH;
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDeadOrEscaped()) {
                addToBot(new ApplyPowerAction(monster, p, new StrengthPower(monster, enemyStrength), enemyStrength));
                addToBot(new ApplyPowerAction(monster, p, new LoseStrengthPower(monster, enemyStrength), enemyStrength));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = "Everyone takes 1 damage. NL You gain 2 temporary Strength and the ennemies gain 1.";
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Hustle();
    }
}
