import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './surveillance.reducer';
import { ISurveillance } from 'app/shared/model/surveillance.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISurveillanceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SurveillanceDetail = (props: ISurveillanceDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { surveillanceEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="ekiliApp.surveillance.detail.title">Surveillance</Translate> [<b>{surveillanceEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="infirmier">
              <Translate contentKey="ekiliApp.surveillance.infirmier">Infirmier</Translate>
            </span>
          </dt>
          <dd>{surveillanceEntity.infirmier}</dd>
          <dt>
            <span id="poste">
              <Translate contentKey="ekiliApp.surveillance.poste">Poste</Translate>
            </span>
          </dt>
          <dd>{surveillanceEntity.poste}</dd>
          <dt>
            <span id="generateur">
              <Translate contentKey="ekiliApp.surveillance.generateur">Generateur</Translate>
            </span>
          </dt>
          <dd>{surveillanceEntity.generateur}</dd>
          <dt>
            <span id="statut">
              <Translate contentKey="ekiliApp.surveillance.statut">Statut</Translate>
            </span>
          </dt>
          <dd>{surveillanceEntity.statut}</dd>
          <dt>
            <span id="poid">
              <Translate contentKey="ekiliApp.surveillance.poid">Poid</Translate>
            </span>
          </dt>
          <dd>{surveillanceEntity.poid}</dd>
          <dt>
            <span id="ufnet">
              <Translate contentKey="ekiliApp.surveillance.ufnet">Ufnet</Translate>
            </span>
          </dt>
          <dd>{surveillanceEntity.ufnet}</dd>
          <dt>
            <span id="etatConscience">
              <Translate contentKey="ekiliApp.surveillance.etatConscience">Etat Conscience</Translate>
            </span>
          </dt>
          <dd>{surveillanceEntity.etatConscience}</dd>
          <dt>
            <span id="eupneique">
              <Translate contentKey="ekiliApp.surveillance.eupneique">Eupneique</Translate>
            </span>
          </dt>
          <dd>{surveillanceEntity.eupneique}</dd>
          <dt>
            <span id="restitutionPar">
              <Translate contentKey="ekiliApp.surveillance.restitutionPar">Restitution Par</Translate>
            </span>
          </dt>
          <dd>{surveillanceEntity.restitutionPar}</dd>
          <dt>
            <span id="autreComplication">
              <Translate contentKey="ekiliApp.surveillance.autreComplication">Autre Complication</Translate>
            </span>
          </dt>
          <dd>{surveillanceEntity.autreComplication}</dd>
          <dt>
            <span id="observation">
              <Translate contentKey="ekiliApp.surveillance.observation">Observation</Translate>
            </span>
          </dt>
          <dd>{surveillanceEntity.observation}</dd>
          <dt>
            <Translate contentKey="ekiliApp.surveillance.traitement">Traitement</Translate>
          </dt>
          <dd>{surveillanceEntity.traitement ? surveillanceEntity.traitement.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/surveillance" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/surveillance/${surveillanceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ surveillance }: IRootState) => ({
  surveillanceEntity: surveillance.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SurveillanceDetail);
