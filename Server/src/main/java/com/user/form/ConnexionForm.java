package com.user.form;



import javax.servlet.http.HttpServletRequest;

import com.db.DAO;
import com.user.services.User;
import com.user.services.UserDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ConnexionForm extends Form_MD5{
	private static final String CHAMP_EMAIL  = "email";
	private static final String CHAMP_PASS   = "motdepasse";
	private int status; 
	private UserDAO adminDAO;


	private String resultat;
	private Map<String, String> erreurs      = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public User connecterUtilisateur( HttpServletRequest request ) {
		/* R�cup�ration des champs du formulaire */
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String motDePasse = getValeurChamp( request, CHAMP_PASS );

		User user = new user();

		/* Validation du champ email et mot de passe. */
		try {
			validationEMAIL_MP(request);
			
		} catch ( Exception e ) {
			setErreur( CHAMP_EMAIL, e.getMessage() );
			setErreur( CHAMP_PASS, e.getMessage() );
		}
		user.setEmail( email );
		user.setPassword(motDePasse);


		/* Initialisation du résultat global de la validation. */
		if ( status == 1) {
			resultat = "Succès de la connexion.";
		} else {
			resultat = "Échec de la connexion.";
		}
		return user;
		
	}


	/**
	 * Valide l'adresse email saisie.
	 */

	private void validationEMAIL_MP( HttpServletRequest request )  {
		String email = getValeurChamp( request, CHAMP_EMAIL );

		String password = this.MD5(getValeurChamp( request, CHAMP_PASS ));

		DAO dao = DAO.getInstance();
		this.UserDAO = dao.getUserDAO();

		List<User> allUser = UserDAO.getAll();

		for(int i = 0; i< allUser.size();i++)
		{
			User user = allUser.get(i);
			String emailrecup = user.getEmail();
			String passrecup = user.getPassword(); 
	

			if (emailrecup.equals(email) & passrecup.equals(password) ) { 
				status =1;
				
			}else {
				status = 0;
			}
		}
	}
	/*
	 * Ajoute un message correspondant au champ sp�cifi� � la map des erreurs.
	 */
	private void setErreur( String champ, String message ) {
		erreurs.put( champ, message );
	}

	/*
	 * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} else {
			return valeur;
		}
	}
}