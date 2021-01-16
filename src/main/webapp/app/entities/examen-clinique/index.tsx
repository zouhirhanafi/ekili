import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ExamenClinique from './examen-clinique';
import ExamenCliniqueDetail from './examen-clinique-detail';
import ExamenCliniqueUpdate from './examen-clinique-update';
import ExamenCliniqueDeleteDialog from './examen-clinique-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ExamenCliniqueUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ExamenCliniqueUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ExamenCliniqueDetail} />
      <ErrorBoundaryRoute path={match.url} component={ExamenClinique} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ExamenCliniqueDeleteDialog} />
  </>
);

export default Routes;
