import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dossier-patient.reducer';
import { IDossierPatient } from 'app/shared/model/dossier-patient.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDossierPatientDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DossierPatientDetail = (props: IDossierPatientDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dossierPatientEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="ekiliApp.dossierPatient.detail.title">DossierPatient</Translate> [<b>{dossierPatientEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="ip">
              <Translate contentKey="ekiliApp.dossierPatient.ip">Ip</Translate>
            </span>
          </dt>
          <dd>{dossierPatientEntity.ip}</dd>
          <dt>
            <span id="nom">
              <Translate contentKey="ekiliApp.dossierPatient.nom">Nom</Translate>
            </span>
          </dt>
          <dd>{dossierPatientEntity.nom}</dd>
          <dt>
            <span id="prenom">
              <Translate contentKey="ekiliApp.dossierPatient.prenom">Prenom</Translate>
            </span>
          </dt>
          <dd>{dossierPatientEntity.prenom}</dd>
          <dt>
            <span id="genre">
              <Translate contentKey="ekiliApp.dossierPatient.genre">Genre</Translate>
            </span>
          </dt>
          <dd>{dossierPatientEntity.genre}</dd>
          <dt>
            <span id="tel">
              <Translate contentKey="ekiliApp.dossierPatient.tel">Tel</Translate>
            </span>
          </dt>
          <dd>{dossierPatientEntity.tel}</dd>
          <dt>
            <span id="adresse">
              <Translate contentKey="ekiliApp.dossierPatient.adresse">Adresse</Translate>
            </span>
          </dt>
          <dd>{dossierPatientEntity.adresse}</dd>
          <dt>
            <span id="amo">
              <Translate contentKey="ekiliApp.dossierPatient.amo">Amo</Translate>
            </span>
          </dt>
          <dd>{dossierPatientEntity.amo}</dd>
          <dt>
            <span id="typeCentreOrigine">
              <Translate contentKey="ekiliApp.dossierPatient.typeCentreOrigine">Type Centre Origine</Translate>
            </span>
          </dt>
          <dd>{dossierPatientEntity.typeCentreOrigine}</dd>
          <dt>
            <span id="villeCentreOrigine">
              <Translate contentKey="ekiliApp.dossierPatient.villeCentreOrigine">Ville Centre Origine</Translate>
            </span>
          </dt>
          <dd>{dossierPatientEntity.villeCentreOrigine}</dd>
          <dt>
            <span id="observation">
              <Translate contentKey="ekiliApp.dossierPatient.observation">Observation</Translate>
            </span>
          </dt>
          <dd>{dossierPatientEntity.observation}</dd>
          <dt>
            <span id="naissance">
              <Translate contentKey="ekiliApp.dossierPatient.naissance">Naissance</Translate>
            </span>
          </dt>
          <dd>
            {dossierPatientEntity.naissance ? (
              <TextFormat value={dossierPatientEntity.naissance} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="ekiliApp.dossierPatient.antecedent">Antecedent</Translate>
          </dt>
          <dd>{dossierPatientEntity.antecedent ? dossierPatientEntity.antecedent.id : ''}</dd>
          <dt>
            <Translate contentKey="ekiliApp.dossierPatient.diagnostic">Diagnostic</Translate>
          </dt>
          <dd>{dossierPatientEntity.diagnostic ? dossierPatientEntity.diagnostic.id : ''}</dd>
          <dt>
            <Translate contentKey="ekiliApp.dossierPatient.indicationHd">Indication Hd</Translate>
          </dt>
          <dd>{dossierPatientEntity.indicationHd ? dossierPatientEntity.indicationHd.id : ''}</dd>
          <dt>
            <Translate contentKey="ekiliApp.dossierPatient.examenClinique">Examen Clinique</Translate>
          </dt>
          <dd>{dossierPatientEntity.examenClinique ? dossierPatientEntity.examenClinique.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/dossier-patient" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dossier-patient/${dossierPatientEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dossierPatient }: IRootState) => ({
  dossierPatientEntity: dossierPatient.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DossierPatientDetail);
