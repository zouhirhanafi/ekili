import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './examen-clinique.reducer';
import { IExamenClinique } from 'app/shared/model/examen-clinique.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExamenCliniqueProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ExamenClinique = (props: IExamenCliniqueProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { examenCliniqueList, match, loading } = props;
  return (
    <div>
      <h2 id="examen-clinique-heading">
        <Translate contentKey="ekiliApp.examenClinique.home.title">Examen Cliniques</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="ekiliApp.examenClinique.home.createLabel">Create new Examen Clinique</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {examenCliniqueList && examenCliniqueList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenClinique.gcs">Gcs</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenClinique.pa">Pa</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenClinique.diurese">Diurese</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenClinique.autre">Autre</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {examenCliniqueList.map((examenClinique, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${examenClinique.id}`} color="link" size="sm">
                      {examenClinique.id}
                    </Button>
                  </td>
                  <td>{examenClinique.gcs}</td>
                  <td>{examenClinique.pa}</td>
                  <td>{examenClinique.diurese}</td>
                  <td>{examenClinique.autre}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${examenClinique.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${examenClinique.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${examenClinique.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="ekiliApp.examenClinique.home.notFound">No Examen Cliniques found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ examenClinique }: IRootState) => ({
  examenCliniqueList: examenClinique.entities,
  loading: examenClinique.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ExamenClinique);
