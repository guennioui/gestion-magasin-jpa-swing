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
    public Client findClientById(String id,EntityManager entityManager) throws ClientNotExistException{
        Optional<Client> optionalClient = Optional.ofNullable(entityManager.find(Client.class, id));
        if (optionalClient.isEmpty()) {
            throw new ClientNotExistException("le client que vous rechercher"+id+"n'existe pas");
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

}
