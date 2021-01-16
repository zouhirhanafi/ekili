import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Antecedent from './antecedent';
import AntecedentDetail from './antecedent-detail';
import AntecedentUpdate from './antecedent-update';
import AntecedentDeleteDialog from './antecedent-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AntecedentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AntecedentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AntecedentDetail} />
      <ErrorBoundaryRoute path={match.url} component={Antecedent} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AntecedentDeleteDialog} />
  </>
);

export default Routes;
