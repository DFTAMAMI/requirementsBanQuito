package ec.edu.espe.banquito.requirements.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ec.edu.espe.banquito.requirements.controller.DTO.InterestAccrueRQ;
import ec.edu.espe.banquito.requirements.controller.DTO.InterestAccrueRS;
import ec.edu.espe.banquito.requirements.controller.DTO.InterestAccrueUpdateRQ;
import ec.edu.espe.banquito.requirements.model.InterestAccrue;
import ec.edu.espe.banquito.requirements.repository.InterestAccrueRepository;
import jakarta.transaction.Transactional;

@Service
public class InterestAccrueService {
    private final InterestAccrueRepository interestAccrueRepository;

    public InterestAccrueService(InterestAccrueRepository interestAccrueRepository) {
        this.interestAccrueRepository = interestAccrueRepository;
    }

    public InterestAccrueRS obtain(Integer id) {
        Optional<InterestAccrue> interestAccrueOpt = this.interestAccrueRepository.findById(id);
        if (interestAccrueOpt.isPresent()) {
            return this.transformInterestAccrue(interestAccrueOpt.get());
        } else {
            return null;
        }
    }

    public List<InterestAccrueRS> getAllInterestAccrue() {
        List<InterestAccrue> assets = this.interestAccrueRepository.findAll();
        List<InterestAccrueRS> assetsList = new ArrayList<>();
        for (InterestAccrue asset : assets) {
            assetsList.add(this.transformInterestAccrue(asset));
        }
        return assetsList;
    }

    @Transactional
    public InterestAccrueRS createInterest(InterestAccrueRQ assetRQ) {
        try {
            InterestAccrue asset = this.transformInterestAccrueRQ(assetRQ);
            this.interestAccrueRepository.save(asset);
            return this.transformInterestAccrue(asset);
        } catch (RuntimeException rte) {
            throw new RuntimeException("Error al crear el interés: " + rte.getMessage(), rte);
        }
    }

    @Transactional
    public InterestAccrueRS updateInterest(InterestAccrueUpdateRQ rq) {

        Optional<InterestAccrue> assetOpt = this.interestAccrueRepository.findById(rq.getId());

        if (assetOpt.isPresent()) {
            InterestAccrue assetTmp = assetOpt.get();

            InterestAccrue asset = this.transformOfAssetUpdateRQ(rq);

            assetTmp.setInterestRate(asset.getInterestRate());
            assetTmp.setInterestType(asset.getInterestType());
            assetTmp.setSpread(asset.getSpread());
            assetTmp.setChargeFrecuency(asset.getChargeFrecuency());

            this.interestAccrueRepository.save(assetTmp);

            return this.transformInterestAccrue(asset);
        } else {
            throw new RuntimeException("Acumulación de intereses no encontrado");
        }
    }

    @Transactional
    public void deleteInterest(Integer id) {
        try {
            Optional<InterestAccrue> interestAccrueOpt = this.interestAccrueRepository.findById(id);
            if (interestAccrueOpt.isPresent()) {
                this.interestAccrueRepository.delete(interestAccrueOpt.get());
            } else {
                throw new RuntimeException("La acumulación de intereses no esta registrada: " + id);
            }
        } catch (RuntimeException rte) {
            throw new RuntimeException("No se puede eliminar la acumulación de intereses con Codigo: " + id, rte);
        }
    }

    private InterestAccrue transformInterestAccrueRQ(InterestAccrueRQ rq) {
        InterestAccrue branch = InterestAccrue
                .builder()
                .interestRate(rq.getInterestRate())
                .interestType(rq.getInterestType())
                .spread(rq.getSpread())
                .chargeFrecuency(rq.getChargeFrecuency())
                .build();
        return branch;
    }

    private InterestAccrueRS transformInterestAccrue(InterestAccrue asset) {
        InterestAccrueRS assetRS = InterestAccrueRS
                .builder()
                .id(asset.getId())
                .interestRate(asset.getInterestRate())
                .interestType(asset.getInterestType())
                .spread(asset.getSpread())
                .chargeFrecuency(asset.getChargeFrecuency())
                .build();
        return assetRS;
    }

    private InterestAccrue transformOfAssetUpdateRQ(InterestAccrueUpdateRQ rq) {
        InterestAccrue branch = InterestAccrue
                .builder()
                .id(rq.getId())
                .interestRate(rq.getInterestRate())
                .interestType(rq.getInterestType())
                .spread(rq.getSpread())
                .chargeFrecuency(rq.getChargeFrecuency())
                .build();
        return branch;
    }

}
