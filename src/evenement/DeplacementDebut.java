package evenement;

import carte.Direction;
import carte.Carte;
import carte.Case;
import robot.*;

public class DeplacementDebut extends Evenement {
	private Robot robot;
	private Direction dir;
	private Carte carte;
	
	public DeplacementDebut(long date, Robot robot, Direction dir, Carte carte) {
		super(date);
		this.robot = robot;
		this.dir = dir;
		this.carte = carte;
	}

	@Override
	public void execute() {
		Case caseActuelle = this.robot.getPosition();
		int lig = caseActuelle.getLigne();
		int col = caseActuelle.getColonne();
		switch (dir) {
		case NORD:
			lig--;
			break;
		case SUD:
			lig++;
			break;
		case OUEST:
			col--;
			break;
		case EST:
			col++;
			break;
		}
		
		if (col < 0 || lig < 0 || col > (carte.getNbColonnes() - 1) || lig > (carte.getNbLignes() - 1)) {
			throw new IllegalArgumentException("Le robot sort de la carte");
		} else {
			Case newPosition = carte.getCase(lig, col);
			this.robot.setPosition(newPosition);
		}
	}
}