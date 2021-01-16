import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DossierPatient from './dossier-patient';
import DossierPatientDetail from './dossier-patient-detail';
import DossierPatientUpdate from './dossier-patient-update';
import DossierPatientDeleteDialog from './dossier-patient-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DossierPatientUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DossierPatientUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DossierPatientDetail} />
      <ErrorBoundaryRoute path={match.url} component={DossierPatient} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DossierPatientDeleteDialog} />
  </>
);

export default Routes;
