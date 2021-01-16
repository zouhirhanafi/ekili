import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './examen-bioligique.reducer';
import { IExamenBioligique } from 'app/shared/model/examen-bioligique.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExamenBioligiqueDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ExamenBioligiqueDetail = (props: IExamenBioligiqueDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { examenBioligiqueEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="ekiliApp.examenBioligique.detail.title">ExamenBioligique</Translate> [<b>{examenBioligiqueEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="date">
              <Translate contentKey="ekiliApp.examenBioligique.date">Date</Translate>
            </span>
          </dt>
          <dd>
            {examenBioligiqueEntity.date ? (
              <TextFormat value={examenBioligiqueEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="uree">
              <Translate contentKey="ekiliApp.examenBioligique.uree">Uree</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.uree}</dd>
          <dt>
            <span id="creat">
              <Translate contentKey="ekiliApp.examenBioligique.creat">Creat</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.creat}</dd>
          <dt>
            <span id="k">
              <Translate contentKey="ekiliApp.examenBioligique.k">K</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.k}</dd>
          <dt>
            <span id="na">
              <Translate contentKey="ekiliApp.examenBioligique.na">Na</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.na}</dd>
          <dt>
            <span id="ca">
              <Translate contentKey="ekiliApp.examenBioligique.ca">Ca</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.ca}</dd>
          <dt>
            <span id="crp">
              <Translate contentKey="ekiliApp.examenBioligique.crp">Crp</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.crp}</dd>
          <dt>
            <span id="hb">
              <Translate contentKey="ekiliApp.examenBioligique.hb">Hb</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.hb}</dd>
          <dt>
            <span id="gb">
              <Translate contentKey="ekiliApp.examenBioligique.gb">Gb</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.gb}</dd>
          <dt>
            <span id="plt">
              <Translate contentKey="ekiliApp.examenBioligique.plt">Plt</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.plt}</dd>
          <dt>
            <span id="acHbs">
              <Translate contentKey="ekiliApp.examenBioligique.acHbs">Ac Hbs</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.acHbs}</dd>
          <dt>
            <span id="agHbs">
              <Translate contentKey="ekiliApp.examenBioligique.agHbs">Ag Hbs</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.agHbs}</dd>
          <dt>
            <span id="hbc">
              <Translate contentKey="ekiliApp.examenBioligique.hbc">Hbc</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.hbc}</dd>
          <dt>
            <span id="acHvc">
              <Translate contentKey="ekiliApp.examenBioligique.acHvc">Ac Hvc</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.acHvc}</dd>
          <dt>
            <span id="vih">
              <Translate contentKey="ekiliApp.examenBioligique.vih">Vih</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.vih}</dd>
          <dt>
            <span id="autre">
              <Translate contentKey="ekiliApp.examenBioligique.autre">Autre</Translate>
            </span>
          </dt>
          <dd>{examenBioligiqueEntity.autre}</dd>
          <dt>
            <Translate contentKey="ekiliApp.examenBioligique.patient">Patient</Translate>
          </dt>
          <dd>{examenBioligiqueEntity.patient ? examenBioligiqueEntity.patient.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/examen-bioligique" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/examen-bioligique/${examenBioligiqueEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ examenBioligique }: IRootState) => ({
  examenBioligiqueEntity: examenBioligique.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ExamenBioligiqueDetail);
