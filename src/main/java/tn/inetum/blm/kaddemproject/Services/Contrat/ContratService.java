package tn.inetum.blm.kaddemproject.Services.Contrat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.inetum.blm.kaddemproject.Entities.Contrat;
import tn.inetum.blm.kaddemproject.Entities.Etudiant;
import tn.inetum.blm.kaddemproject.Repository.ContratRepository;
import tn.inetum.blm.kaddemproject.Repository.EtudiantRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ContratService implements IContratService{

    private  final ContratRepository contratRepository;
    private  final EtudiantRepository etudiantRepository;

    @Override
    public List<Contrat> retrieveAllContrats() {
        return (List<Contrat>) contratRepository.findAll();
    }

    @Override
    public Contrat updateContrat(Contrat ce) {
        return contratRepository.save(ce);
    }

    @Override
    public Contrat addContrat(Contrat ce) {
        return contratRepository.save(ce);
    }

    @Override
    public Contrat retrieveContrat(Integer idContrat) {
        return contratRepository.findById(idContrat).orElse(null);
    }

    @Override
    public void removeContrat(Integer idContrat) {
        contratRepository.delete(retrieveContrat(idContrat));

    }

    @Override
    public Contrat affectContratToEtudiant(Contrat ce, String nomE, String prenomE) {
        Etudiant etudiant = etudiantRepository.findByNomAndPrenom(nomE,prenomE).orElse(null);
        if (etudiant == null || etudiant.getContrats().size() >= 5) {
            return null;
        }
        // Assign the contract to the student
        ce.setEtudiant(etudiant);
        etudiant.getContrats().add(ce);
        etudiantRepository.save(etudiant);
        return ce;
    }
}