import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './prescription.reducer';
import { IPrescription } from 'app/shared/model/prescription.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IPrescriptionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Prescription = (props: IPrescriptionProps) => {
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE), props.location.search)
  );

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get('sort');
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const { prescriptionList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="prescription-heading">
        <Translate contentKey="ekiliApp.prescription.home.title">Prescriptions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="ekiliApp.prescription.home.createLabel">Create new Prescription</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {prescriptionList && prescriptionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('duree')}>
                  <Translate contentKey="ekiliApp.prescription.duree">Duree</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('capillaire')}>
                  <Translate contentKey="ekiliApp.prescription.capillaire">Capillaire</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('restitutionP')}>
                  <Translate contentKey="ekiliApp.prescription.restitutionP">Restitution P</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('niveauUrgence')}>
                  <Translate contentKey="ekiliApp.prescription.niveauUrgence">Niveau Urgence</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ufTotale')}>
                  <Translate contentKey="ekiliApp.prescription.ufTotale">Uf Totale</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rincage')}>
                  <Translate contentKey="ekiliApp.prescription.rincage">Rincage</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('transfusion')}>
                  <Translate contentKey="ekiliApp.prescription.transfusion">Transfusion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('datePlanification')}>
                  <Translate contentKey="ekiliApp.prescription.datePlanification">Date Planification</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('circuit')}>
                  <Translate contentKey="ekiliApp.prescription.circuit">Circuit</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('abordVasculaire')}>
                  <Translate contentKey="ekiliApp.prescription.abordVasculaire">Abord Vasculaire</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('profil')}>
                  <Translate contentKey="ekiliApp.prescription.profil">Profil</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('conductiviteP')}>
                  <Translate contentKey="ekiliApp.prescription.conductiviteP">Conductivite P</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('debitPompe')}>
                  <Translate contentKey="ekiliApp.prescription.debitPompe">Debit Pompe</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('temperatureDialysat')}>
                  <Translate contentKey="ekiliApp.prescription.temperatureDialysat">Temperature Dialysat</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('atc')}>
                  <Translate contentKey="ekiliApp.prescription.atc">Atc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hnfh0')}>
                  <Translate contentKey="ekiliApp.prescription.hnfh0">Hnfh 0</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hnfh2')}>
                  <Translate contentKey="ekiliApp.prescription.hnfh2">Hnfh 2</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hbpm')}>
                  <Translate contentKey="ekiliApp.prescription.hbpm">Hbpm</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('statut')}>
                  <Translate contentKey="ekiliApp.prescription.statut">Statut</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('motifAnnulation')}>
                  <Translate contentKey="ekiliApp.prescription.motifAnnulation">Motif Annulation</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('motifReport')}>
                  <Translate contentKey="ekiliApp.prescription.motifReport">Motif Report</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('observationP')}>
                  <Translate contentKey="ekiliApp.prescription.observationP">Observation P</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="ekiliApp.prescription.traitement">Traitement</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="ekiliApp.prescription.surveillance">Surveillance</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="ekiliApp.prescription.patient">Patient</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {prescriptionList.map((prescription, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${prescription.id}`} color="link" size="sm">
                      {prescription.id}
                    </Button>
                  </td>
                  <td>{prescription.duree}</td>
                  <td>{prescription.capillaire}</td>
                  <td>{prescription.restitutionP}</td>
                  <td>{prescription.niveauUrgence}</td>
                  <td>{prescription.ufTotale}</td>
                  <td>{prescription.rincage}</td>
                  <td>{prescription.transfusion}</td>
                  <td>
                    {prescription.datePlanification ? (
                      <TextFormat type="date" value={prescription.datePlanification} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{prescription.circuit}</td>
                  <td>{prescription.abordVasculaire}</td>
                  <td>{prescription.profil}</td>
                  <td>{prescription.conductiviteP}</td>
                  <td>{prescription.debitPompe}</td>
                  <td>{prescription.temperatureDialysat}</td>
                  <td>{prescription.atc ? 'true' : 'false'}</td>
                  <td>{prescription.hnfh0}</td>
                  <td>{prescription.hnfh2}</td>
                  <td>{prescription.hbpm}</td>
                  <td>
                    <Translate contentKey={`ekiliApp.StatutPrescription.${prescription.statut}`} />
                  </td>
                  <td>{prescription.motifAnnulation}</td>
                  <td>{prescription.motifReport}</td>
                  <td>{prescription.observationP}</td>
                  <td>
                    {prescription.traitement ? (
                      <Link to={`traitement-perdialyse/${prescription.traitement.id}`}>{prescription.traitement.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {prescription.surveillance ? (
                      <Link to={`surveillance/${prescription.surveillance.id}`}>{prescription.surveillance.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {prescription.patient ? <Link to={`dossier-patient/${prescription.patient.id}`}>{prescription.patient.id}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${prescription.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${prescription.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${prescription.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                      >
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
              <Translate contentKey="ekiliApp.prescription.home.notFound">No Prescriptions found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={prescriptionList && prescriptionList.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={props.totalItems}
            />
          </Row>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

const mapStateToProps = ({ prescription }: IRootState) => ({
  prescriptionList: prescription.entities,
  loading: prescription.loading,
  totalItems: prescription.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Prescription);
