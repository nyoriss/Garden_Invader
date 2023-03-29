package garden_invader.projectileObserver;

/**
 * Interface décrivant un observateur d'entité.
 * Un observateur peut être notifié lorsqu'une entité entre en collision avec une projectile.
 */
public interface EntityObserver {

    /**
     * Méthode appelée pour actualiser la position et les dimensions de l'entité observée.
     * @param posX la position en x de l'entité observée
     * @param posY la position en y de l'entité observée
     * @param width la largeur de l'entité observée
     * @param height la hauteur de l'entité observée
     * @return vrai si une collision est détectée, faux sinon.
     */
    boolean actualiser(int posX, int posY, int width, int height);

    /**
     * Méthode appelée lorsqu'une collision est détectée entre l'entité observée et un projectile.
     * @param projectile le projectile entré en collision avec l'entité observée
     * @return vrai si l'entité est détruite suite à la collision, faux sinon.
     */
    boolean hurt(Projectile projectile);
}
