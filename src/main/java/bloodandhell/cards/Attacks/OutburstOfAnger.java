package bloodandhell.cards.Attacks;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;

public class OutburstOfAnger extends BaseCard {
    public static final String ID = makeID(OutburstOfAnger.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            3
    );

    public OutburstOfAnger() {
        super(ID, info);
        // On initialise ici une variable pour les dégâts basés sur les dégâts subis
        this.baseDamage = GameActionManager.damageReceivedThisCombat;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int dmg2 = GameActionManager.damageReceivedThisCombat;
        addToBot(new DamageAction(m, new DamageInfo(p, dmg2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void applyPowers() {
        // Met à jour les dégâts avant d'appliquer les pouvoirs (comme les buffs)
        this.baseDamage = GameActionManager.damageReceivedThisCombat;
        super.applyPowers();
        // Met à jour la description de la carte pour afficher les dégâts dynamiquement
        this.rawDescription = "Deal (" + this.baseDamage + ") damage to ALL ennemies. Damage equal to the HP lost this combat.";

        initializeDescription();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new OutburstOfAnger();
    }
}
