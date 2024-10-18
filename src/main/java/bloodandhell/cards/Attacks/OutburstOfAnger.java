package bloodandhell.cards.Attacks;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OutburstOfAnger extends BaseCard {
    public static final String ID = makeID(OutburstOfAnger.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            3 // Coût initial de la carte
    );

    public OutburstOfAnger() {
        super(ID, info);
        this.isMultiDamage = true; // Indique que la carte inflige des dégâts à tous les ennemis
        this.baseDamage = GameActionManager.damageReceivedThisCombat; // Initialise la base des dégâts
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Dégâts égaux aux dégâts subis par le joueur pendant ce combat
        int damageTaken = GameActionManager.damageReceivedThisCombat;
        this.baseDamage = damageTaken;

        // Infliger des dégâts à tous les ennemis
        addToBot(new DamageAllEnemiesAction(
                p,
                DamageInfo.createDamageMatrix(damageTaken, true),
                DamageInfo.DamageType.NORMAL,
                AbstractGameAction.AttackEffect.SLASH_VERTICAL
        ));
    }

    @Override
    public void applyPowers() {
        // Met à jour les dégâts basés sur les dégâts subis pendant le combat
        this.baseDamage = GameActionManager.damageReceivedThisCombat;
        super.applyPowers();

        // Met à jour la description pour afficher les dégâts dynamiquement
        this.rawDescription = "Deal (" + this.baseDamage + ") damage to ALL enemies. Damage equal to the HP lost this combat.";
        initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2); // Réduire le coût de la carte en cas d'amélioration
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new OutburstOfAnger();
    }
}
