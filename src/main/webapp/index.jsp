<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/dataTables.bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.1.0/css/buttons.bootstrap.css"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/fixedcolumns/3.2.0/css/fixedColumns.bootstrap.css"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/fixedheader/3.1.0/css/fixedHeader.bootstrap.css"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/responsive/2.0.0/css/responsive.bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/scroller/1.4.0/css/scroller.bootstrap.css"/>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.js"></script>
    <script type="text/javascript" src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.js"></script>
    <script type="text/javascript" src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.1.0/js/dataTables.buttons.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.1.0/js/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.1.0/js/buttons.flash.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.1.0/js/buttons.html5.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.1.0/js/buttons.print.js"></script>
    <script type="text/javascript"
            src="https://cdn.datatables.net/fixedcolumns/3.2.0/js/dataTables.fixedColumns.js"></script>
    <script type="text/javascript"
            src="https://cdn.datatables.net/fixedheader/3.1.0/js/dataTables.fixedHeader.js"></script>
    <script type="text/javascript"
            src="https://cdn.datatables.net/responsive/2.0.0/js/dataTables.responsive.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/scroller/1.4.0/js/dataTables.scroller.js"></script>
    <style>
        .vertical-center {
            min-height: 100%; /* Fallback for browsers do NOT support vh unit */
            min-height: 100vh; /* These two lines are counted as one :-)       */

            display: flex;
            align-items: center;
        }

    </style>
    <script type="text/javascript">
        var cardDataTable = {};
        Number.prototype.format = function (n, x) {
            var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
            return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$&,');
        };

        function handleData(data) {
            var dataArray = data.split(",");
            var jsonData = new Array();
            for (i = 0; i < dataArray.length; i++) {
                var jsonArg = new Object();
                jsonArg.id = dataArray[i];
                i++;
                jsonArg.name = dataArray[i];
                jsonData.push(jsonArg);
            }
            var decks = [];
            $.each(jsonData, function (j, deck) {
                decks.push('<li><a href="#" data-id="' + deck.id + '" onclick="loadDeck(this)">' + deck.name + '</a></li>');
            });
            $('#decks').append(decks);
        }



        function loadDeck(identifier) {
            $.ajax({
                url: "loadDeck",
                data: {deckId: $(identifier).data('id')},
                method: "Post",
                success: function (data) {
                    loadCards(data);
                }
            });
        }
        function searchCards(searchText) {
            $.ajax({
                url: "searchCard",
                data: {cardName: searchText},
                method: "Post",
                success: function (data) {
                    loadCards(data);
                }
            });
        }


        function loadCards(data) {
            cardDataTable.clear().rows.add(data).draw();
        }
        $.ajax({
            url: "loadDeck",
            method: "GET",
            dataType: 'text',
            success: function (data) {
                handleData(data);
            }
        });

        $(document).ready(function () {


            $("#search").click(function () {
                        searchCards($('#searchText').val());
                    }
            );
            cardDataTable = $('#cardTable').DataTable({
                columns: [
                    {data: 'id'},
                    {data: 'name'},
                    {data: 'set'},
                    {data: 'cardLevel'},
                    {data: 'cardNum'},
                    {data: 'style'},
                    {data: 'rarity'},
                    {data: 'type'},
                    {
                        data: 'arkPrice',
                        render: function (data, type, row) {

                            return '$' + data.format(2);
                        }

                    },
                    {
                        data: 'arkUrl',
                        render: function (data, type, row) {

                            return '<a href"' + data.arkUrl + '">Arkadia Gaming</a>';
                        }

                    },
                    {
                        data: 'dbzoPrice',
                        render: function (data, type, row) {

                            return '$' + data.format(2);
                        }

                    },
                    {
                        data: 'dbzoUrl',
                        render: function (data, type, row) {

                            return '<a href"' + data + '"> DBZ Exchange </a>';
                        }
                    }
                ],
                buttons: [
                    'copy', 'excel', 'pdf'
                ],
                scrollY:        '50vh',
                scrollCollapse: true,
                paging:         false



            });

        });

    </script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home <span class="sr-only">(current)</span></a></li>
            <li><a href="#">DBZ</a></li>
        </ul>
    </div>
</nav>
<div class="jumbotron vertical-center">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-5 col-sm-3">
            </div>
            <div class="col-xs-1 col-sm-1">
		<span class="pull-right">
			<button type="button" id="reload" class="btn btn-default" aria-label="Right Align">
                <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
            </button>
			</span>
            </div>
            <div class="col-xs-6 col-sm-4">
                <div class="input-group">
                    <input type="text" id="searchText" class="form-control" placeholder="Search for...">
				<span class="input-group-btn">
					<button class="btn btn-default" id="search" type="button">Search!</button>
				</span>
                </div><!-- /input-group -->
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-primary">Decks</button>
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu" id="decks">

                </ul>
            </div>
        </div>
        <div class="row">
            <table id="cardTable" class="cell-border">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Card Name</th>
                        <th>Card Set</th>
                        <th>Card Level</th>
                        <th>Card Number</th>
                        <th>Card Style</th>
                        <th>Card Rarity</th>
                        <th>Card Type</th>
                        <th>Card Price (Arkadia Gaming)</th>
                        <th>Card Link (Arkadia Gaming)</th>
                        <th>Card Price (DBZ Exchange)</th>
                        <th>Card Link (DBZ Exchange)</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
                <tfoot>
                <tr>
                    <th>Id</th>
                    <th>Card Name</th>
                    <th>Card Set</th>
                    <th>Card Level</th>
                    <th>Card Number</th>
                    <th>Card Style</th>
                    <th>Card Rarity</th>
                    <th>Card Type</th>
                    <th>Card Price (Arkadia Gaming)</th>
                    <th>Card Link (Arkadia Gaming)</th>
                    <th>Card Price (DBZ Exchange)</th>
                    <th>Card Link (DBZ Exchange)</th>
                </tr>

                </tfoot>

            </table>

        </div>
    </div>

</div>
</body>
</html>