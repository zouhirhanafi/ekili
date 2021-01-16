import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './examen-bioligique.reducer';
import { IExamenBioligique } from 'app/shared/model/examen-bioligique.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExamenBioligiqueProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ExamenBioligique = (props: IExamenBioligiqueProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { examenBioligiqueList, match, loading } = props;
  return (
    <div>
      <h2 id="examen-bioligique-heading">
        <Translate contentKey="ekiliApp.examenBioligique.home.title">Examen Bioligiques</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="ekiliApp.examenBioligique.home.createLabel">Create new Examen Bioligique</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {examenBioligiqueList && examenBioligiqueList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.uree">Uree</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.creat">Creat</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.k">K</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.na">Na</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.ca">Ca</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.crp">Crp</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.hb">Hb</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.gb">Gb</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.plt">Plt</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.acHbs">Ac Hbs</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.agHbs">Ag Hbs</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.hbc">Hbc</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.acHvc">Ac Hvc</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.vih">Vih</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.autre">Autre</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.examenBioligique.patient">Patient</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {examenBioligiqueList.map((examenBioligique, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${examenBioligique.id}`} color="link" size="sm">
                      {examenBioligique.id}
                    </Button>
                  </td>
                  <td>
                    {examenBioligique.date ? <TextFormat type="date" value={examenBioligique.date} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{examenBioligique.uree}</td>
                  <td>{examenBioligique.creat}</td>
                  <td>{examenBioligique.k}</td>
                  <td>{examenBioligique.na}</td>
                  <td>{examenBioligique.ca}</td>
                  <td>{examenBioligique.crp}</td>
                  <td>{examenBioligique.hb}</td>
                  <td>{examenBioligique.gb}</td>
                  <td>{examenBioligique.plt}</td>
                  <td>{examenBioligique.acHbs}</td>
                  <td>{examenBioligique.agHbs}</td>
                  <td>{examenBioligique.hbc}</td>
                  <td>{examenBioligique.acHvc}</td>
                  <td>{examenBioligique.vih}</td>
                  <td>{examenBioligique.autre}</td>
                  <td>
                    {examenBioligique.patient ? (
                      <Link to={`dossier-patient/${examenBioligique.patient.id}`}>{examenBioligique.patient.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${examenBioligique.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${examenBioligique.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${examenBioligique.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="ekiliApp.examenBioligique.home.notFound">No Examen Bioligiques found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ examenBioligique }: IRootState) => ({
  examenBioligiqueList: examenBioligique.entities,
  loading: examenBioligique.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ExamenBioligique);
