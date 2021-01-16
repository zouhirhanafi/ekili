import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAntecedent } from 'app/shared/model/antecedent.model';
import { getEntities as getAntecedents } from 'app/entities/antecedent/antecedent.reducer';
import { IDiagnostic } from 'app/shared/model/diagnostic.model';
import { getEntities as getDiagnostics } from 'app/entities/diagnostic/diagnostic.reducer';
import { IIndicationHd } from 'app/shared/model/indication-hd.model';
import { getEntities as getIndicationHds } from 'app/entities/indication-hd/indication-hd.reducer';
import { IExamenClinique } from 'app/shared/model/examen-clinique.model';
import { getEntities as getExamenCliniques } from 'app/entities/examen-clinique/examen-clinique.reducer';
import { getEntity, updateEntity, createEntity, reset } from './dossier-patient.reducer';
import { IDossierPatient } from 'app/shared/model/dossier-patient.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDossierPatientUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DossierPatientUpdate = (props: IDossierPatientUpdateProps) => {
  const [antecedentId, setAntecedentId] = useState('0');
  const [diagnosticId, setDiagnosticId] = useState('0');
  const [indicationHdId, setIndicationHdId] = useState('0');
  const [examenCliniqueId, setExamenCliniqueId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dossierPatientEntity, antecedents, diagnostics, indicationHds, examenCliniques, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/dossier-patient' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAntecedents();
    props.getDiagnostics();
    props.getIndicationHds();
    props.getExamenCliniques();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...dossierPatientEntity,
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
          <h2 id="ekiliApp.dossierPatient.home.createOrEditLabel">
            <Translate contentKey="ekiliApp.dossierPatient.home.createOrEditLabel">Create or edit a DossierPatient</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dossierPatientEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dossier-patient-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dossier-patient-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="ipLabel" for="dossier-patient-ip">
                  <Translate contentKey="ekiliApp.dossierPatient.ip">Ip</Translate>
                </Label>
                <AvField
                  id="dossier-patient-ip"
                  type="string"
                  className="form-control"
                  name="ip"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nomLabel" for="dossier-patient-nom">
                  <Translate contentKey="ekiliApp.dossierPatient.nom">Nom</Translate>
                </Label>
                <AvField
                  id="dossier-patient-nom"
                  type="text"
                  name="nom"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="prenomLabel" for="dossier-patient-prenom">
                  <Translate contentKey="ekiliApp.dossierPatient.prenom">Prenom</Translate>
                </Label>
                <AvField
                  id="dossier-patient-prenom"
                  type="text"
                  name="prenom"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="genreLabel" for="dossier-patient-genre">
                  <Translate contentKey="ekiliApp.dossierPatient.genre">Genre</Translate>
                </Label>
                <AvField
                  id="dossier-patient-genre"
                  type="string"
                  className="form-control"
                  name="genre"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="telLabel" for="dossier-patient-tel">
                  <Translate contentKey="ekiliApp.dossierPatient.tel">Tel</Translate>
                </Label>
                <AvField id="dossier-patient-tel" type="text" name="tel" />
              </AvGroup>
              <AvGroup>
                <Label id="adresseLabel" for="dossier-patient-adresse">
                  <Translate contentKey="ekiliApp.dossierPatient.adresse">Adresse</Translate>
                </Label>
                <AvField id="dossier-patient-adresse" type="text" name="adresse" />
              </AvGroup>
              <AvGroup>
                <Label id="amoLabel" for="dossier-patient-amo">
                  <Translate contentKey="ekiliApp.dossierPatient.amo">Amo</Translate>
                </Label>
                <AvField id="dossier-patient-amo" type="string" className="form-control" name="amo" />
              </AvGroup>
              <AvGroup>
                <Label id="typeCentreOrigineLabel" for="dossier-patient-typeCentreOrigine">
                  <Translate contentKey="ekiliApp.dossierPatient.typeCentreOrigine">Type Centre Origine</Translate>
                </Label>
                <AvField id="dossier-patient-typeCentreOrigine" type="string" className="form-control" name="typeCentreOrigine" />
              </AvGroup>
              <AvGroup>
                <Label id="villeCentreOrigineLabel" for="dossier-patient-villeCentreOrigine">
                  <Translate contentKey="ekiliApp.dossierPatient.villeCentreOrigine">Ville Centre Origine</Translate>
                </Label>
                <AvField id="dossier-patient-villeCentreOrigine" type="string" className="form-control" name="villeCentreOrigine" />
              </AvGroup>
              <AvGroup>
                <Label id="observationLabel" for="dossier-patient-observation">
                  <Translate contentKey="ekiliApp.dossierPatient.observation">Observation</Translate>
                </Label>
                <AvField id="dossier-patient-observation" type="text" name="observation" />
              </AvGroup>
              <AvGroup>
                <Label id="naissanceLabel" for="dossier-patient-naissance">
                  <Translate contentKey="ekiliApp.dossierPatient.naissance">Naissance</Translate>
                </Label>
                <AvField id="dossier-patient-naissance" type="date" className="form-control" name="naissance" />
              </AvGroup>
              <AvGroup>
                <Label for="dossier-patient-antecedent">
                  <Translate contentKey="ekiliApp.dossierPatient.antecedent">Antecedent</Translate>
                </Label>
                <AvInput id="dossier-patient-antecedent" type="select" className="form-control" name="antecedent.id">
                  <option value="" key="0" />
                  {antecedents
                    ? antecedents.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="dossier-patient-diagnostic">
                  <Translate contentKey="ekiliApp.dossierPatient.diagnostic">Diagnostic</Translate>
                </Label>
                <AvInput id="dossier-patient-diagnostic" type="select" className="form-control" name="diagnostic.id">
                  <option value="" key="0" />
                  {diagnostics
                    ? diagnostics.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="dossier-patient-indicationHd">
                  <Translate contentKey="ekiliApp.dossierPatient.indicationHd">Indication Hd</Translate>
                </Label>
                <AvInput id="dossier-patient-indicationHd" type="select" className="form-control" name="indicationHd.id">
                  <option value="" key="0" />
                  {indicationHds
                    ? indicationHds.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="dossier-patient-examenClinique">
                  <Translate contentKey="ekiliApp.dossierPatient.examenClinique">Examen Clinique</Translate>
                </Label>
                <AvInput id="dossier-patient-examenClinique" type="select" className="form-control" name="examenClinique.id">
                  <option value="" key="0" />
                  {examenCliniques
                    ? examenCliniques.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/dossier-patient" replace color="info">
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
  antecedents: storeState.antecedent.entities,
  diagnostics: storeState.diagnostic.entities,
  indicationHds: storeState.indicationHd.entities,
  examenCliniques: storeState.examenClinique.entities,
  dossierPatientEntity: storeState.dossierPatient.entity,
  loading: storeState.dossierPatient.loading,
  updating: storeState.dossierPatient.updating,
  updateSuccess: storeState.dossierPatient.updateSuccess,
});

const mapDispatchToProps = {
  getAntecedents,
  getDiagnostics,
  getIndicationHds,
  getExamenCliniques,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DossierPatientUpdate);
