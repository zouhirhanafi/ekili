import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITraitementPerdialyse } from 'app/shared/model/traitement-perdialyse.model';
import { getEntities as getTraitementPerdialyses } from 'app/entities/traitement-perdialyse/traitement-perdialyse.reducer';
import { ISurveillance } from 'app/shared/model/surveillance.model';
import { getEntities as getSurveillances } from 'app/entities/surveillance/surveillance.reducer';
import { IDossierPatient } from 'app/shared/model/dossier-patient.model';
import { getEntities as getDossierPatients } from 'app/entities/dossier-patient/dossier-patient.reducer';
import { getEntity, updateEntity, createEntity, reset } from './prescription.reducer';
import { IPrescription } from 'app/shared/model/prescription.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPrescriptionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PrescriptionUpdate = (props: IPrescriptionUpdateProps) => {
  const [traitementId, setTraitementId] = useState('0');
  const [surveillanceId, setSurveillanceId] = useState('0');
  const [patientId, setPatientId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { prescriptionEntity, traitementPerdialyses, surveillances, dossierPatients, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/prescription' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTraitementPerdialyses();
    props.getSurveillances();
    props.getDossierPatients();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...prescriptionEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="ekiliApp.prescription.home.createOrEditLabel">
            <Translate contentKey="ekiliApp.prescription.home.createOrEditLabel">Create or edit a Prescription</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : prescriptionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="prescription-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="prescription-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dureeLabel" for="prescription-duree">
                  <Translate contentKey="ekiliApp.prescription.duree">Duree</Translate>
                </Label>
                <AvField id="prescription-duree" type="string" className="form-control" name="duree" />
              </AvGroup>
              <AvGroup>
                <Label id="capillaireLabel" for="prescription-capillaire">
                  <Translate contentKey="ekiliApp.prescription.capillaire">Capillaire</Translate>
                </Label>
                <AvField id="prescription-capillaire" type="string" className="form-control" name="capillaire" />
              </AvGroup>
              <AvGroup>
                <Label id="restitutionPLabel" for="prescription-restitutionP">
                  <Translate contentKey="ekiliApp.prescription.restitutionP">Restitution P</Translate>
                </Label>
                <AvField id="prescription-restitutionP" type="string" className="form-control" name="restitutionP" />
              </AvGroup>
              <AvGroup>
                <Label id="niveauUrgenceLabel" for="prescription-niveauUrgence">
                  <Translate contentKey="ekiliApp.prescription.niveauUrgence">Niveau Urgence</Translate>
                </Label>
                <AvField id="prescription-niveauUrgence" type="string" className="form-control" name="niveauUrgence" />
              </AvGroup>
              <AvGroup>
                <Label id="ufTotaleLabel" for="prescription-ufTotale">
                  <Translate contentKey="ekiliApp.prescription.ufTotale">Uf Totale</Translate>
                </Label>
                <AvField id="prescription-ufTotale" type="string" className="form-control" name="ufTotale" />
              </AvGroup>
              <AvGroup>
                <Label id="rincageLabel" for="prescription-rincage">
                  <Translate contentKey="ekiliApp.prescription.rincage">Rincage</Translate>
                </Label>
                <AvField id="prescription-rincage" type="string" className="form-control" name="rincage" />
              </AvGroup>
              <AvGroup>
                <Label id="transfusionLabel" for="prescription-transfusion">
                  <Translate contentKey="ekiliApp.prescription.transfusion">Transfusion</Translate>
                </Label>
                <AvField id="prescription-transfusion" type="string" className="form-control" name="transfusion" />
              </AvGroup>
              <AvGroup>
                <Label id="datePlanificationLabel" for="prescription-datePlanification">
                  <Translate contentKey="ekiliApp.prescription.datePlanification">Date Planification</Translate>
                </Label>
                <AvField id="prescription-datePlanification" type="date" className="form-control" name="datePlanification" />
              </AvGroup>
              <AvGroup>
                <Label id="circuitLabel" for="prescription-circuit">
                  <Translate contentKey="ekiliApp.prescription.circuit">Circuit</Translate>
                </Label>
                <AvField id="prescription-circuit" type="string" className="form-control" name="circuit" />
              </AvGroup>
              <AvGroup>
                <Label id="abordVasculaireLabel" for="prescription-abordVasculaire">
                  <Translate contentKey="ekiliApp.prescription.abordVasculaire">Abord Vasculaire</Translate>
                </Label>
                <AvField id="prescription-abordVasculaire" type="string" className="form-control" name="abordVasculaire" />
              </AvGroup>
              <AvGroup>
                <Label id="profilLabel" for="prescription-profil">
                  <Translate contentKey="ekiliApp.prescription.profil">Profil</Translate>
                </Label>
                <AvField id="prescription-profil" type="string" className="form-control" name="profil" />
              </AvGroup>
              <AvGroup>
                <Label id="conductivitePLabel" for="prescription-conductiviteP">
                  <Translate contentKey="ekiliApp.prescription.conductiviteP">Conductivite P</Translate>
                </Label>
                <AvField id="prescription-conductiviteP" type="string" className="form-control" name="conductiviteP" />
              </AvGroup>
              <AvGroup>
                <Label id="debitPompeLabel" for="prescription-debitPompe">
                  <Translate contentKey="ekiliApp.prescription.debitPompe">Debit Pompe</Translate>
                </Label>
                <AvField id="prescription-debitPompe" type="string" className="form-control" name="debitPompe" />
              </AvGroup>
              <AvGroup>
                <Label id="temperatureDialysatLabel" for="prescription-temperatureDialysat">
                  <Translate contentKey="ekiliApp.prescription.temperatureDialysat">Temperature Dialysat</Translate>
                </Label>
                <AvField id="prescription-temperatureDialysat" type="string" className="form-control" name="temperatureDialysat" />
              </AvGroup>
              <AvGroup check>
                <Label id="atcLabel">
                  <AvInput id="prescription-atc" type="checkbox" className="form-check-input" name="atc" />
                  <Translate contentKey="ekiliApp.prescription.atc">Atc</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="hnfh0Label" for="prescription-hnfh0">
                  <Translate contentKey="ekiliApp.prescription.hnfh0">Hnfh 0</Translate>
                </Label>
                <AvField id="prescription-hnfh0" type="string" className="form-control" name="hnfh0" />
              </AvGroup>
              <AvGroup>
                <Label id="hnfh2Label" for="prescription-hnfh2">
                  <Translate contentKey="ekiliApp.prescription.hnfh2">Hnfh 2</Translate>
                </Label>
                <AvField id="prescription-hnfh2" type="string" className="form-control" name="hnfh2" />
              </AvGroup>
              <AvGroup>
                <Label id="hbpmLabel" for="prescription-hbpm">
                  <Translate contentKey="ekiliApp.prescription.hbpm">Hbpm</Translate>
                </Label>
                <AvField id="prescription-hbpm" type="string" className="form-control" name="hbpm" />
              </AvGroup>
              <AvGroup>
                <Label id="statutLabel" for="prescription-statut">
                  <Translate contentKey="ekiliApp.prescription.statut">Statut</Translate>
                </Label>
                <AvInput
                  id="prescription-statut"
                  type="select"
                  className="form-control"
                  name="statut"
                  value={(!isNew && prescriptionEntity.statut) || 'TERMINEE'}
                >
                  <option value="TERMINEE">{translate('ekiliApp.StatutPrescription.TERMINEE')}</option>
                  <option value="AVENIR">{translate('ekiliApp.StatutPrescription.AVENIR')}</option>
                  <option value="ANNULEE">{translate('ekiliApp.StatutPrescription.ANNULEE')}</option>
                  <option value="REPORTEE">{translate('ekiliApp.StatutPrescription.REPORTEE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="motifAnnulationLabel" for="prescription-motifAnnulation">
                  <Translate contentKey="ekiliApp.prescription.motifAnnulation">Motif Annulation</Translate>
                </Label>
                <AvField id="prescription-motifAnnulation" type="string" className="form-control" name="motifAnnulation" />
              </AvGroup>
              <AvGroup>
                <Label id="motifReportLabel" for="prescription-motifReport">
                  <Translate contentKey="ekiliApp.prescription.motifReport">Motif Report</Translate>
                </Label>
                <AvField id="prescription-motifReport" type="string" className="form-control" name="motifReport" />
              </AvGroup>
              <AvGroup>
                <Label id="observationPLabel" for="prescription-observationP">
                  <Translate contentKey="ekiliApp.prescription.observationP">Observation P</Translate>
                </Label>
                <AvField id="prescription-observationP" type="text" name="observationP" />
              </AvGroup>
              <AvGroup>
                <Label for="prescription-traitement">
                  <Translate contentKey="ekiliApp.prescription.traitement">Traitement</Translate>
                </Label>
                <AvInput id="prescription-traitement" type="select" className="form-control" name="traitement.id">
                  <option value="" key="0" />
                  {traitementPerdialyses
                    ? traitementPerdialyses.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="prescription-surveillance">
                  <Translate contentKey="ekiliApp.prescription.surveillance">Surveillance</Translate>
                </Label>
                <AvInput id="prescription-surveillance" type="select" className="form-control" name="surveillance.id">
                  <option value="" key="0" />
                  {surveillances
                    ? surveillances.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="prescription-patient">
                  <Translate contentKey="ekiliApp.prescription.patient">Patient</Translate>
                </Label>
                <AvInput id="prescription-patient" type="select" className="form-control" name="patient.id">
                  <option value="" key="0" />
                  {dossierPatients
                    ? dossierPatients.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/prescription" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  traitementPerdialyses: storeState.traitementPerdialyse.entities,
  surveillances: storeState.surveillance.entities,
  dossierPatients: storeState.dossierPatient.entities,
  prescriptionEntity: storeState.prescription.entity,
  loading: storeState.prescription.loading,
  updating: storeState.prescription.updating,
  updateSuccess: storeState.prescription.updateSuccess,
});

const mapDispatchToProps = {
  getTraitementPerdialyses,
  getSurveillances,
  getDossierPatients,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PrescriptionUpdate);
