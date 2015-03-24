task 'A1' => 'A1.pdf'
file 'A1.pdf' => 'A1.md' do |t|
  sh "pandoc #{t.source} -o #{t.name}"
end
