import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ITraitementPerdialyse } from 'app/shared/model/traitement-perdialyse.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './traitement-perdialyse.reducer';

export interface ITraitementPerdialyseDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TraitementPerdialyseDeleteDialog = (props: ITraitementPerdialyseDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/traitement-perdialyse');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.traitementPerdialyseEntity.id);
  };

  const { traitementPerdialyseEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="ekiliApp.traitementPerdialyse.delete.question">
        <Translate contentKey="ekiliApp.traitementPerdialyse.delete.question" interpolate={{ id: traitementPerdialyseEntity.id }}>
          Are you sure you want to delete this TraitementPerdialyse?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-traitementPerdialyse" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ traitementPerdialyse }: IRootState) => ({
  traitementPerdialyseEntity: traitementPerdialyse.entity,
  updateSuccess: traitementPerdialyse.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TraitementPerdialyseDeleteDialog);
