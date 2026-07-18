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

public class FirstStrike extends BaseCard {
    public static final String ID = makeID(FirstStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 20;

    private static final int BASE_SELF_DAMAGE = 10;

    private int boostedSelfDamage;  // Dégâts infligés au joueur

    public FirstStrike() {
        super(ID, info);
        setDamage(DAMAGE);
        setInnate(false, true); // Innée uniquement une fois améliorée.
        this.boostedSelfDamage = BASE_SELF_DAMAGE;
        updateDescription(false);
        tags.add(CardTags.STRIKE);  // Marque la carte comme une carte de type Strike
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Infliger les dégâts à l'ennemi
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));

        // Si l'ennemi n'est pas à plein HP, infliger des dégâts au joueur
        if (m != null && m.currentHealth < m.maxHealth) {
            addToBot(new DamageAction(p, new DamageInfo(p, this.boostedSelfDamage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true; // Pas de changement de statistiques : seule l'amélioration rend la carte Innée.
            updateDescription(true);
        }
    }

    private void updateDescription(boolean upgraded) {
        String baseDesc = cardStrings.DESCRIPTION;
        if (upgraded) {
            baseDesc = cardStrings.UPGRADE_DESCRIPTION;
        }

        // Mise à jour de la description avec la nouvelle valeur de selfDamage
        this.rawDescription = baseDesc.replace("!SD!", String.valueOf(this.boostedSelfDamage));
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new FirstStrike();
    }
}
