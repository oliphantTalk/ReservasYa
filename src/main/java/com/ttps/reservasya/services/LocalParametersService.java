package com.ttps.reservasya.services;

import com.ttps.reservasya.controllers.panel.form.LocalParamsForm;
import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.repository.LocalParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nahuelbarrena on 02/01/19
 */
@Service
public class LocalParametersService {

    public LocalParametersRepository localParametersRepository;

    @Autowired
    public LocalParametersService(LocalParametersRepository localParametersRepository) {
        this.localParametersRepository = localParametersRepository;
    }

    public LocalParameters getLocalParameters(){
        return localParametersRepository.findAll().get(0);
    }

    public LocalParameters saveLocalParameters(LocalParamsForm localParameters){
        LocalParameters anterior = this.getLocalParameters();
        anterior.setGapMax(localParameters.getGapMax());
        anterior.setPuntosPorPeso(localParameters.getPuntosPorPeso());
        anterior.setPesosPorPunto(localParameters.getPesosPorPunto());
        anterior.setFirstClassRate(localParameters.getFirstClassRate());
        anterior.setFactorVueloEscala(localParameters.getFactorVueloEscala());
        anterior.setFactorServicioVuelo(localParameters.getFactorServicioVuelo());
        anterior.setBusinessClassRate(localParameters.getBusinessClassRate());
        anterior.setFactorDevolucion(localParameters.getFactorDevolucion());
        return localParametersRepository.save(anterior);
    }
}
