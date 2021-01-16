import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDossierPatient } from 'app/shared/model/dossier-patient.model';
import { getEntities as getDossierPatients } from 'app/entities/dossier-patient/dossier-patient.reducer';
import { getEntity, updateEntity, createEntity, reset } from './examen-bioligique.reducer';
import { IExamenBioligique } from 'app/shared/model/examen-bioligique.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IExamenBioligiqueUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ExamenBioligiqueUpdate = (props: IExamenBioligiqueUpdateProps) => {
  const [patientId, setPatientId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { examenBioligiqueEntity, dossierPatients, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/examen-bioligique');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
        ...examenBioligiqueEntity,
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
          <h2 id="ekiliApp.examenBioligique.home.createOrEditLabel">
            <Translate contentKey="ekiliApp.examenBioligique.home.createOrEditLabel">Create or edit a ExamenBioligique</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : examenBioligiqueEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="examen-bioligique-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="examen-bioligique-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateLabel" for="examen-bioligique-date">
                  <Translate contentKey="ekiliApp.examenBioligique.date">Date</Translate>
                </Label>
                <AvField id="examen-bioligique-date" type="date" className="form-control" name="date" />
              </AvGroup>
              <AvGroup>
                <Label id="ureeLabel" for="examen-bioligique-uree">
                  <Translate contentKey="ekiliApp.examenBioligique.uree">Uree</Translate>
                </Label>
                <AvField id="examen-bioligique-uree" type="string" className="form-control" name="uree" />
              </AvGroup>
              <AvGroup>
                <Label id="creatLabel" for="examen-bioligique-creat">
                  <Translate contentKey="ekiliApp.examenBioligique.creat">Creat</Translate>
                </Label>
                <AvField id="examen-bioligique-creat" type="string" className="form-control" name="creat" />
              </AvGroup>
              <AvGroup>
                <Label id="kLabel" for="examen-bioligique-k">
                  <Translate contentKey="ekiliApp.examenBioligique.k">K</Translate>
                </Label>
                <AvField id="examen-bioligique-k" type="string" className="form-control" name="k" />
              </AvGroup>
              <AvGroup>
                <Label id="naLabel" for="examen-bioligique-na">
                  <Translate contentKey="ekiliApp.examenBioligique.na">Na</Translate>
                </Label>
                <AvField id="examen-bioligique-na" type="string" className="form-control" name="na" />
              </AvGroup>
              <AvGroup>
                <Label id="caLabel" for="examen-bioligique-ca">
                  <Translate contentKey="ekiliApp.examenBioligique.ca">Ca</Translate>
                </Label>
                <AvField id="examen-bioligique-ca" type="string" className="form-control" name="ca" />
              </AvGroup>
              <AvGroup>
                <Label id="crpLabel" for="examen-bioligique-crp">
                  <Translate contentKey="ekiliApp.examenBioligique.crp">Crp</Translate>
                </Label>
                <AvField id="examen-bioligique-crp" type="string" className="form-control" name="crp" />
              </AvGroup>
              <AvGroup>
                <Label id="hbLabel" for="examen-bioligique-hb">
                  <Translate contentKey="ekiliApp.examenBioligique.hb">Hb</Translate>
                </Label>
                <AvField id="examen-bioligique-hb" type="string" className="form-control" name="hb" />
              </AvGroup>
              <AvGroup>
                <Label id="gbLabel" for="examen-bioligique-gb">
                  <Translate contentKey="ekiliApp.examenBioligique.gb">Gb</Translate>
                </Label>
                <AvField id="examen-bioligique-gb" type="string" className="form-control" name="gb" />
              </AvGroup>
              <AvGroup>
                <Label id="pltLabel" for="examen-bioligique-plt">
                  <Translate contentKey="ekiliApp.examenBioligique.plt">Plt</Translate>
                </Label>
                <AvField id="examen-bioligique-plt" type="string" className="form-control" name="plt" />
              </AvGroup>
              <AvGroup>
                <Label id="acHbsLabel" for="examen-bioligique-acHbs">
                  <Translate contentKey="ekiliApp.examenBioligique.acHbs">Ac Hbs</Translate>
                </Label>
                <AvField id="examen-bioligique-acHbs" type="text" name="acHbs" />
              </AvGroup>
              <AvGroup>
                <Label id="agHbsLabel" for="examen-bioligique-agHbs">
                  <Translate contentKey="ekiliApp.examenBioligique.agHbs">Ag Hbs</Translate>
                </Label>
                <AvField id="examen-bioligique-agHbs" type="string" className="form-control" name="agHbs" />
              </AvGroup>
              <AvGroup>
                <Label id="hbcLabel" for="examen-bioligique-hbc">
                  <Translate contentKey="ekiliApp.examenBioligique.hbc">Hbc</Translate>
                </Label>
                <AvField id="examen-bioligique-hbc" type="text" name="hbc" />
              </AvGroup>
              <AvGroup>
                <Label id="acHvcLabel" for="examen-bioligique-acHvc">
                  <Translate contentKey="ekiliApp.examenBioligique.acHvc">Ac Hvc</Translate>
                </Label>
                <AvField id="examen-bioligique-acHvc" type="text" name="acHvc" />
              </AvGroup>
              <AvGroup>
                <Label id="vihLabel" for="examen-bioligique-vih">
                  <Translate contentKey="ekiliApp.examenBioligique.vih">Vih</Translate>
                </Label>
                <AvField id="examen-bioligique-vih" type="text" name="vih" />
              </AvGroup>
              <AvGroup>
                <Label id="autreLabel" for="examen-bioligique-autre">
                  <Translate contentKey="ekiliApp.examenBioligique.autre">Autre</Translate>
                </Label>
                <AvField id="examen-bioligique-autre" type="text" name="autre" />
              </AvGroup>
              <AvGroup>
                <Label for="examen-bioligique-patient">
                  <Translate contentKey="ekiliApp.examenBioligique.patient">Patient</Translate>
                </Label>
                <AvInput id="examen-bioligique-patient" type="select" className="form-control" name="patient.id">
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
              <Button tag={Link} id="cancel-save" to="/examen-bioligique" replace color="info">
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
  dossierPatients: storeState.dossierPatient.entities,
  examenBioligiqueEntity: storeState.examenBioligique.entity,
  loading: storeState.examenBioligique.loading,
  updating: storeState.examenBioligique.updating,
  updateSuccess: storeState.examenBioligique.updateSuccess,
});

const mapDispatchToProps = {
  getDossierPatients,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ExamenBioligiqueUpdate);
