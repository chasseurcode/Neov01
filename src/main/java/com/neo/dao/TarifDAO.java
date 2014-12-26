package com.neo.dao;

import java.util.List;

import com.neo.domaine.Tarif;
import com.neo.domaine.TarifAppel;
import com.neo.domaine.TarifNotification;
import com.neo.domaine.TarifTextuelle;

public interface TarifDAO {

	public void creer(TarifNotification notif);
	public void creer(TarifAppel appel);
	public void creer(TarifTextuelle texte);
	public void modifier(TarifAppel appel);
	public void modifier(TarifNotification notif);
	public void updateAllApp();
	public void updateAllText();
	public void updateAllNotif();
	public void modifier(TarifTextuelle texte);
	public void supprimer(TarifAppel appel);
	public void supprimer(TarifNotification tarif);
	public void supprimer(TarifTextuelle tarif);
	public void modifier(Tarif tarif);
	public TarifAppel findTarifAppelById(long id);
	public TarifNotification findNotificationById(long id);
	public TarifTextuelle findTarifTextuelleById(long id);
	public List<TarifAppel> listerTarifappel();
	public List<TarifNotification> listerTarifNotif();
	public List<TarifTextuelle> listerTarifText();
}
