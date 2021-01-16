import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './prescription.reducer';
import { IPrescription } from 'app/shared/model/prescription.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPrescriptionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PrescriptionDetail = (props: IPrescriptionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { prescriptionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="ekiliApp.prescription.detail.title">Prescription</Translate> [<b>{prescriptionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="duree">
              <Translate contentKey="ekiliApp.prescription.duree">Duree</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.duree}</dd>
          <dt>
            <span id="capillaire">
              <Translate contentKey="ekiliApp.prescription.capillaire">Capillaire</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.capillaire}</dd>
          <dt>
            <span id="restitutionP">
              <Translate contentKey="ekiliApp.prescription.restitutionP">Restitution P</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.restitutionP}</dd>
          <dt>
            <span id="niveauUrgence">
              <Translate contentKey="ekiliApp.prescription.niveauUrgence">Niveau Urgence</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.niveauUrgence}</dd>
          <dt>
            <span id="ufTotale">
              <Translate contentKey="ekiliApp.prescription.ufTotale">Uf Totale</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.ufTotale}</dd>
          <dt>
            <span id="rincage">
              <Translate contentKey="ekiliApp.prescription.rincage">Rincage</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.rincage}</dd>
          <dt>
            <span id="transfusion">
              <Translate contentKey="ekiliApp.prescription.transfusion">Transfusion</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.transfusion}</dd>
          <dt>
            <span id="datePlanification">
              <Translate contentKey="ekiliApp.prescription.datePlanification">Date Planification</Translate>
            </span>
          </dt>
          <dd>
            {prescriptionEntity.datePlanification ? (
              <TextFormat value={prescriptionEntity.datePlanification} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="circuit">
              <Translate contentKey="ekiliApp.prescription.circuit">Circuit</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.circuit}</dd>
          <dt>
            <span id="abordVasculaire">
              <Translate contentKey="ekiliApp.prescription.abordVasculaire">Abord Vasculaire</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.abordVasculaire}</dd>
          <dt>
            <span id="profil">
              <Translate contentKey="ekiliApp.prescription.profil">Profil</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.profil}</dd>
          <dt>
            <span id="conductiviteP">
              <Translate contentKey="ekiliApp.prescription.conductiviteP">Conductivite P</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.conductiviteP}</dd>
          <dt>
            <span id="debitPompe">
              <Translate contentKey="ekiliApp.prescription.debitPompe">Debit Pompe</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.debitPompe}</dd>
          <dt>
            <span id="temperatureDialysat">
              <Translate contentKey="ekiliApp.prescription.temperatureDialysat">Temperature Dialysat</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.temperatureDialysat}</dd>
          <dt>
            <span id="atc">
              <Translate contentKey="ekiliApp.prescription.atc">Atc</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.atc ? 'true' : 'false'}</dd>
          <dt>
            <span id="hnfh0">
              <Translate contentKey="ekiliApp.prescription.hnfh0">Hnfh 0</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.hnfh0}</dd>
          <dt>
            <span id="hnfh2">
              <Translate contentKey="ekiliApp.prescription.hnfh2">Hnfh 2</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.hnfh2}</dd>
          <dt>
            <span id="hbpm">
              <Translate contentKey="ekiliApp.prescription.hbpm">Hbpm</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.hbpm}</dd>
          <dt>
            <span id="statut">
              <Translate contentKey="ekiliApp.prescription.statut">Statut</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.statut}</dd>
          <dt>
            <span id="motifAnnulation">
              <Translate contentKey="ekiliApp.prescription.motifAnnulation">Motif Annulation</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.motifAnnulation}</dd>
          <dt>
            <span id="motifReport">
              <Translate contentKey="ekiliApp.prescription.motifReport">Motif Report</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.motifReport}</dd>
          <dt>
            <span id="observationP">
              <Translate contentKey="ekiliApp.prescription.observationP">Observation P</Translate>
            </span>
          </dt>
          <dd>{prescriptionEntity.observationP}</dd>
          <dt>
            <Translate contentKey="ekiliApp.prescription.traitement">Traitement</Translate>
          </dt>
          <dd>{prescriptionEntity.traitement ? prescriptionEntity.traitement.id : ''}</dd>
          <dt>
            <Translate contentKey="ekiliApp.prescription.surveillance">Surveillance</Translate>
          </dt>
          <dd>{prescriptionEntity.surveillance ? prescriptionEntity.surveillance.id : ''}</dd>
          <dt>
            <Translate contentKey="ekiliApp.prescription.patient">Patient</Translate>
          </dt>
          <dd>{prescriptionEntity.patient ? prescriptionEntity.patient.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/prescription" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/prescription/${prescriptionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ prescription }: IRootState) => ({
  prescriptionEntity: prescription.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PrescriptionDetail);
