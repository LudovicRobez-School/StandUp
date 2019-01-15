<?php

if (isset($_POST['submit']))
{
   /* on test si les champ sont bien remplis */
    if(!empty($_POST['Nom']) and !empty($_POST['Prenom']) and !empty($_POST['Adresse']) and !empty($_POST['MDP']))
    {
        /* on test si le mdp contient bien au moins 6 caractère et maximum 40 caractère */
        if (strlen($_POST['MDP'])>=4 && strlen($_POST['MDP']) <=40)
        {
      /*
              //on test si les deux mdp sont bien identique [béta]
            if ($_POST['password']==$_POST['repeatpassword'])
            {
    */
                // On crypte le mot de passe
                $_POST['MDP']= md5($_POST['MDP']);
                // on se connecte à MySQL et on sélectionne la base
                $c = new mysqli("127.0.0.1","root","","ecobank");
                //On créé la requête
                $sql = "INSERT INTO newclient VALUES ('".$_POST['Nom']."','".$_POST['Prenom']."','".$_POST['Adresse']."','".$_POST['MDP']."')";

                /* execute et affiche l'erreur mysql si elle se produit */
                if(!$c->query($sql))
                {
                    printf("Message d'erreur : %s\n", $c->error);
                }
      /*      // on ferme la connexion [béta]
            mysqli_close($c);
            }
            else echo "Les mots de passe ne sont pas identiques";
        }
        else echo "Le mot de passe est trop court !";
    }
    else echo "Veuillez saisir tous les champs !";
} */
?>
