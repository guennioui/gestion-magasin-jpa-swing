package ma.emsi.IDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import ma.emsi.IDao.IDaoClient;
import ma.emsi.entities.Client;
import ma.emsi.entities.Commande;
import ma.emsi.exceptions.ClientNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IDaoClientImpl implements IDaoClient {

    public IDaoClientImpl() {
    }

    @Override
    public void addNewClient(Client client, EntityManager entityManager) {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
    }

    @Override
    public void modifyClient(Client client, Client newClient,EntityManager entityManager) {
    }

    @Override
    public Client findClientById(Client client,EntityManager entityManager) throws ClientNotExistException{
            Optional<Client> optionalClient = Optional.ofNullable(entityManager.find(Client.class, client.getId()));
            if (optionalClient.isEmpty()) {
                throw new ClientNotExistException("le client que vous rechercher"+client.getId()+"n'existe pas");
            }
        return optionalClient.get();
    }

    @Override
    public void removeClient(Client client, EntityManager entityManager) throws ClientNotExistException{
            Optional<Client> optionalClient = Optional.ofNullable(entityManager.find(Client.class, client.getId()));
            if (optionalClient.isEmpty()) {
                throw new ClientNotExistException("le client que vous rechercher"+client.getId()+"n'existe pas");
            }else {
                entityManager.getTransaction().begin();
                entityManager.remove(client);
                entityManager.getTransaction().commit();
            }
    }

    @Override
    public List<Client> findAllClients(EntityManager entityManager) {
        TypedQuery<Client> query = entityManager.createNamedQuery("Client.findAll", Client.class);
        return new ArrayList<Client>(query.getResultList());
    }

    public void addCommandeToClient(Client client, Commande commande, EntityManager entityManager) throws ClientNotExistException{
            entityManager.getTransaction().begin();
            Optional<Client> optionalClient = Optional.ofNullable(entityManager.find(Client.class, client.getId()));
            if (optionalClient.isEmpty()) {
                throw new ClientNotExistException("le client que vous rechercher "+client.getId()+" n'existe pas");
            }else{
                System.out.println("la commande: "+commande.getNumero()+" a ete bien persister");
                optionalClient.get().getCommandes().add(commande);
                System.out.println("la commande: "+commande.getNumero()+" a ete bien ajouter au client: "+client.getId());
            }
            entityManager.getTransaction().commit();
    }

    public void addCommandesToClient(Client client, List<Commande> commandes, EntityManager entityManager) throws ClientNotExistException {

            entityManager.getTransaction().begin();
            Optional<Client> optionalClient = Optional.ofNullable(entityManager.find(Client.class, client.getId()));
            if (optionalClient.isEmpty()) {
                throw new ClientNotExistException("le client que vous rechercher " + client.getId() + " n'existe pas");
            } else {
                optionalClient.get().getCommandes().addAll(commandes);
            }
            entityManager.getTransaction().commit();
    }
}
