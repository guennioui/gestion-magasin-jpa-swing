package ma.emsi.IDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import ma.emsi.entities.Client;
import ma.emsi.entities.Commande;
import ma.emsi.exceptions.ClientNotExistException;

import java.util.List;

public interface IDaoClient {
    Client findClientById(Client client, EntityManager entityManager)throws ClientNotExistException;
    void addNewClient(Client client, EntityManager entityManager);
    void modifyClient(Client client, Client newClient, EntityManager entityManager);
    void removeClient(Client client, EntityManager entityManager) throws ClientNotExistException;
    List<Client> findAllClients(EntityManager entityManager);
    void addCommandeToClient(Client client, Commande commande, EntityManager entityManager) throws ClientNotExistException;
    void addCommandesToClient(Client client, List<Commande> commandes, EntityManager entityManager) throws ClientNotExistException;


}
