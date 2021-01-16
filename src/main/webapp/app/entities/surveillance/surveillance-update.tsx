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
import { getEntity, updateEntity, createEntity, reset } from './surveillance.reducer';
import { ISurveillance } from 'app/shared/model/surveillance.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISurveillanceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SurveillanceUpdate = (props: ISurveillanceUpdateProps) => {
  const [traitementId, setTraitementId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { surveillanceEntity, traitementPerdialyses, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/surveillance' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTraitementPerdialyses();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...surveillanceEntity,
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
          <h2 id="ekiliApp.surveillance.home.createOrEditLabel">
            <Translate contentKey="ekiliApp.surveillance.home.createOrEditLabel">Create or edit a Surveillance</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : surveillanceEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="surveillance-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="surveillance-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="infirmierLabel" for="surveillance-infirmier">
                  <Translate contentKey="ekiliApp.surveillance.infirmier">Infirmier</Translate>
                </Label>
                <AvField
                  id="surveillance-infirmier"
                  type="string"
                  className="form-control"
                  name="infirmier"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="posteLabel" for="surveillance-poste">
                  <Translate contentKey="ekiliApp.surveillance.poste">Poste</Translate>
                </Label>
                <AvField id="surveillance-poste" type="string" className="form-control" name="poste" />
              </AvGroup>
              <AvGroup>
                <Label id="generateurLabel" for="surveillance-generateur">
                  <Translate contentKey="ekiliApp.surveillance.generateur">Generateur</Translate>
                </Label>
                <AvField id="surveillance-generateur" type="string" className="form-control" name="generateur" />
              </AvGroup>
              <AvGroup>
                <Label id="statutLabel" for="surveillance-statut">
                  <Translate contentKey="ekiliApp.surveillance.statut">Statut</Translate>
                </Label>
                <AvInput
                  id="surveillance-statut"
                  type="select"
                  className="form-control"
                  name="statut"
                  value={(!isNew && surveillanceEntity.statut) || 'ENCOURS'}
                >
                  <option value="ENCOURS">{translate('ekiliApp.StatutSurveillance.ENCOURS')}</option>
                  <option value="VALIDEE">{translate('ekiliApp.StatutSurveillance.VALIDEE')}</option>
                  <option value="ANNULEE">{translate('ekiliApp.StatutSurveillance.ANNULEE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="poidLabel" for="surveillance-poid">
                  <Translate contentKey="ekiliApp.surveillance.poid">Poid</Translate>
                </Label>
                <AvField id="surveillance-poid" type="string" className="form-control" name="poid" />
              </AvGroup>
              <AvGroup>
                <Label id="ufnetLabel" for="surveillance-ufnet">
                  <Translate contentKey="ekiliApp.surveillance.ufnet">Ufnet</Translate>
                </Label>
                <AvField id="surveillance-ufnet" type="string" className="form-control" name="ufnet" />
              </AvGroup>
              <AvGroup>
                <Label id="etatConscienceLabel" for="surveillance-etatConscience">
                  <Translate contentKey="ekiliApp.surveillance.etatConscience">Etat Conscience</Translate>
                </Label>
                <AvField id="surveillance-etatConscience" type="string" className="form-control" name="etatConscience" />
              </AvGroup>
              <AvGroup>
                <Label id="eupneiqueLabel" for="surveillance-eupneique">
                  <Translate contentKey="ekiliApp.surveillance.eupneique">Eupneique</Translate>
                </Label>
                <AvField id="surveillance-eupneique" type="string" className="form-control" name="eupneique" />
              </AvGroup>
              <AvGroup>
                <Label id="restitutionParLabel" for="surveillance-restitutionPar">
                  <Translate contentKey="ekiliApp.surveillance.restitutionPar">Restitution Par</Translate>
                </Label>
                <AvField id="surveillance-restitutionPar" type="string" className="form-control" name="restitutionPar" />
              </AvGroup>
              <AvGroup>
                <Label id="autreComplicationLabel" for="surveillance-autreComplication">
                  <Translate contentKey="ekiliApp.surveillance.autreComplication">Autre Complication</Translate>
                </Label>
                <AvField id="surveillance-autreComplication" type="text" name="autreComplication" />
              </AvGroup>
              <AvGroup>
                <Label id="observationLabel" for="surveillance-observation">
                  <Translate contentKey="ekiliApp.surveillance.observation">Observation</Translate>
                </Label>
                <AvField id="surveillance-observation" type="text" name="observation" />
              </AvGroup>
              <AvGroup>
                <Label for="surveillance-traitement">
                  <Translate contentKey="ekiliApp.surveillance.traitement">Traitement</Translate>
                </Label>
                <AvInput id="surveillance-traitement" type="select" className="form-control" name="traitement.id">
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
              <Button tag={Link} id="cancel-save" to="/surveillance" replace color="info">
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
  surveillanceEntity: storeState.surveillance.entity,
  loading: storeState.surveillance.loading,
  updating: storeState.surveillance.updating,
  updateSuccess: storeState.surveillance.updateSuccess,
});

const mapDispatchToProps = {
  getTraitementPerdialyses,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SurveillanceUpdate);
