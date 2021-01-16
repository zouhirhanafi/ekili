import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ISurveillance } from 'app/shared/model/surveillance.model';
import { getEntities as getSurveillances } from 'app/entities/surveillance/surveillance.reducer';
import { getEntity, updateEntity, createEntity, reset } from './mesure-perdialyse.reducer';
import { IMesurePerdialyse } from 'app/shared/model/mesure-perdialyse.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMesurePerdialyseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MesurePerdialyseUpdate = (props: IMesurePerdialyseUpdateProps) => {
  const [surveillanceId, setSurveillanceId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { mesurePerdialyseEntity, surveillances, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/mesure-perdialyse');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getSurveillances();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...mesurePerdialyseEntity,
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
          <h2 id="ekiliApp.mesurePerdialyse.home.createOrEditLabel">
            <Translate contentKey="ekiliApp.mesurePerdialyse.home.createOrEditLabel">Create or edit a MesurePerdialyse</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : mesurePerdialyseEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="mesure-perdialyse-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="mesure-perdialyse-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="heureLabel" for="mesure-perdialyse-heure">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.heure">Heure</Translate>
                </Label>
                <AvField id="mesure-perdialyse-heure" type="text" name="heure" />
              </AvGroup>
              <AvGroup>
                <Label id="poidLabel" for="mesure-perdialyse-poid">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.poid">Poid</Translate>
                </Label>
                <AvField id="mesure-perdialyse-poid" type="string" className="form-control" name="poid" />
              </AvGroup>
              <AvGroup>
                <Label id="taLabel" for="mesure-perdialyse-ta">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.ta">Ta</Translate>
                </Label>
                <AvField id="mesure-perdialyse-ta" type="text" name="ta" />
              </AvGroup>
              <AvGroup>
                <Label id="tpLabel" for="mesure-perdialyse-tp">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.tp">Tp</Translate>
                </Label>
                <AvField id="mesure-perdialyse-tp" type="string" className="form-control" name="tp" />
              </AvGroup>
              <AvGroup>
                <Label id="dextroLabel" for="mesure-perdialyse-dextro">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.dextro">Dextro</Translate>
                </Label>
                <AvField id="mesure-perdialyse-dextro" type="string" className="form-control" name="dextro" />
              </AvGroup>
              <AvGroup>
                <Label id="paLabel" for="mesure-perdialyse-pa">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.pa">Pa</Translate>
                </Label>
                <AvField id="mesure-perdialyse-pa" type="text" name="pa" />
              </AvGroup>
              <AvGroup>
                <Label id="pvLabel" for="mesure-perdialyse-pv">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.pv">Pv</Translate>
                </Label>
                <AvField id="mesure-perdialyse-pv" type="string" className="form-control" name="pv" />
              </AvGroup>
              <AvGroup>
                <Label id="ptmLabel" for="mesure-perdialyse-ptm">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.ptm">Ptm</Translate>
                </Label>
                <AvField id="mesure-perdialyse-ptm" type="string" className="form-control" name="ptm" />
              </AvGroup>
              <AvGroup>
                <Label id="ufhLabel" for="mesure-perdialyse-ufh">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.ufh">Ufh</Translate>
                </Label>
                <AvField id="mesure-perdialyse-ufh" type="string" className="form-control" name="ufh" />
              </AvGroup>
              <AvGroup>
                <Label id="conductiviteLabel" for="mesure-perdialyse-conductivite">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.conductivite">Conductivite</Translate>
                </Label>
                <AvField id="mesure-perdialyse-conductivite" type="string" className="form-control" name="conductivite" />
              </AvGroup>
              <AvGroup>
                <Label id="tdLabel" for="mesure-perdialyse-td">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.td">Td</Translate>
                </Label>
                <AvField id="mesure-perdialyse-td" type="string" className="form-control" name="td" />
              </AvGroup>
              <AvGroup>
                <Label id="dpsLabel" for="mesure-perdialyse-dps">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.dps">Dps</Translate>
                </Label>
                <AvField id="mesure-perdialyse-dps" type="string" className="form-control" name="dps" />
              </AvGroup>
              <AvGroup>
                <Label id="heparineLabel" for="mesure-perdialyse-heparine">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.heparine">Heparine</Translate>
                </Label>
                <AvField id="mesure-perdialyse-heparine" type="string" className="form-control" name="heparine" />
              </AvGroup>
              <AvGroup>
                <Label id="rincageLabel" for="mesure-perdialyse-rincage">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.rincage">Rincage</Translate>
                </Label>
                <AvField id="mesure-perdialyse-rincage" type="string" className="form-control" name="rincage" />
              </AvGroup>
              <AvGroup>
                <Label id="transfusionLabel" for="mesure-perdialyse-transfusion">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.transfusion">Transfusion</Translate>
                </Label>
                <AvField id="mesure-perdialyse-transfusion" type="string" className="form-control" name="transfusion" />
              </AvGroup>
              <AvGroup>
                <Label id="numPocheLabel" for="mesure-perdialyse-numPoche">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.numPoche">Num Poche</Translate>
                </Label>
                <AvField id="mesure-perdialyse-numPoche" type="string" className="form-control" name="numPoche" />
              </AvGroup>
              <AvGroup>
                <Label for="mesure-perdialyse-surveillance">
                  <Translate contentKey="ekiliApp.mesurePerdialyse.surveillance">Surveillance</Translate>
                </Label>
                <AvInput id="mesure-perdialyse-surveillance" type="select" className="form-control" name="surveillance.id">
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
              <Button tag={Link} id="cancel-save" to="/mesure-perdialyse" replace color="info">
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
  surveillances: storeState.surveillance.entities,
  mesurePerdialyseEntity: storeState.mesurePerdialyse.entity,
  loading: storeState.mesurePerdialyse.loading,
  updating: storeState.mesurePerdialyse.updating,
  updateSuccess: storeState.mesurePerdialyse.updateSuccess,
});

const mapDispatchToProps = {
  getSurveillances,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MesurePerdialyseUpdate);
