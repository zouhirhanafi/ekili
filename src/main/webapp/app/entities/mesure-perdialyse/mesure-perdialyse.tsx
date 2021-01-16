import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './mesure-perdialyse.reducer';
import { IMesurePerdialyse } from 'app/shared/model/mesure-perdialyse.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMesurePerdialyseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const MesurePerdialyse = (props: IMesurePerdialyseProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { mesurePerdialyseList, match, loading } = props;
  return (
    <div>
      <h2 id="mesure-perdialyse-heading">
        <Translate contentKey="ekiliApp.mesurePerdialyse.home.title">Mesure Perdialyses</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="ekiliApp.mesurePerdialyse.home.createLabel">Create new Mesure Perdialyse</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {mesurePerdialyseList && mesurePerdialyseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.heure">Heure</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.poid">Poid</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.ta">Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.tp">Tp</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.dextro">Dextro</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.pa">Pa</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.pv">Pv</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.ptm">Ptm</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.ufh">Ufh</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.conductivite">Conductivite</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.td">Td</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.dps">Dps</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.heparine">Heparine</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.rincage">Rincage</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.transfusion">Transfusion</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.numPoche">Num Poche</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.mesurePerdialyse.surveillance">Surveillance</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mesurePerdialyseList.map((mesurePerdialyse, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${mesurePerdialyse.id}`} color="link" size="sm">
                      {mesurePerdialyse.id}
                    </Button>
                  </td>
                  <td>{mesurePerdialyse.heure}</td>
                  <td>{mesurePerdialyse.poid}</td>
                  <td>{mesurePerdialyse.ta}</td>
                  <td>{mesurePerdialyse.tp}</td>
                  <td>{mesurePerdialyse.dextro}</td>
                  <td>{mesurePerdialyse.pa}</td>
                  <td>{mesurePerdialyse.pv}</td>
                  <td>{mesurePerdialyse.ptm}</td>
                  <td>{mesurePerdialyse.ufh}</td>
                  <td>{mesurePerdialyse.conductivite}</td>
                  <td>{mesurePerdialyse.td}</td>
                  <td>{mesurePerdialyse.dps}</td>
                  <td>{mesurePerdialyse.heparine}</td>
                  <td>{mesurePerdialyse.rincage}</td>
                  <td>{mesurePerdialyse.transfusion}</td>
                  <td>{mesurePerdialyse.numPoche}</td>
                  <td>
                    {mesurePerdialyse.surveillance ? (
                      <Link to={`surveillance/${mesurePerdialyse.surveillance.id}`}>{mesurePerdialyse.surveillance.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${mesurePerdialyse.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mesurePerdialyse.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mesurePerdialyse.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="ekiliApp.mesurePerdialyse.home.notFound">No Mesure Perdialyses found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ mesurePerdialyse }: IRootState) => ({
  mesurePerdialyseList: mesurePerdialyse.entities,
  loading: mesurePerdialyse.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MesurePerdialyse);
