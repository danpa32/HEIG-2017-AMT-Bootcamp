<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; HEIG-VD | AMT Bootcamp 2017</p>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="<c:url value="/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/vendor/popper/popper.min.js"/>"></script>
<script src="<c:url value="/vendor/bootstrap/js/bootstrap.min.js"/>"></script>

<!-- Confirmation modal -->
<div class="modal fade" id="confirmDelete" tabindex="-1" role="dialog" aria-labelledby="Confirm delete modal" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmDeleteModalLabel">Are you sure ?</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>If you click on Delete, you will definitivly remove this quote from the database.</p>
                <div class="container mx-auto"><div class="card"></div></div>
                <div class="mt-4 form-check">
                    <label class="form-check-label">
                        <input id="confirmAgain" type="checkbox" class="form-check-input">
                        Don't ask me again
                    </label>
                </div>
            </div>
            <div class="modal-footer">
                <a href="" type="button" class="btn btn-primary" data-dismiss="modal">Stoooop!</a>
                <a id="confirmDeleteLink" href="" type="button" class="btn btn-danger">Delete</a>
            </div>
        </div>
    </div>
</div>

<script>
    $('#confirmDelete').on('show.bs.modal', function (event) {
        var link = $(event.relatedTarget); // Button that triggered the modal
        var href = link.attr('href'); // Extract info from data-* attributes
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var cardCopy = link.parent().parent().prev('.card-body').clone();
        var modal = $(this);
        var confirmLink = modal.find('#confirmDeleteLink');
        confirmLink.attr('href', href);
        modal.find('.card').empty().append(cardCopy);

        // Don't ask me again option
        var again = modal.find('#confirmAgain');
        again.prop('checked', false);
        again.change('change', function () {
            if(again.is(':checked')) {
                confirmLink.attr('href', href + '&confirm=0');
            } else {
                confirmLink.attr('href', href);
            }
        });
    });
</script>
</body>
</html>