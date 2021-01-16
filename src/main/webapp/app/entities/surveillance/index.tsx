import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Surveillance from './surveillance';
import SurveillanceDetail from './surveillance-detail';
import SurveillanceUpdate from './surveillance-update';
import SurveillanceDeleteDialog from './surveillance-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SurveillanceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SurveillanceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SurveillanceDetail} />
      <ErrorBoundaryRoute path={match.url} component={Surveillance} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SurveillanceDeleteDialog} />
  </>
);

export default Routes;
